FROM maven as build

WORKDIR /workspace/app

COPY pom.xml .

COPY src ./src

RUN mvn clean package

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jre-alpine

ARG DEPENDENCY=/workspace/app/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

COPY src/main/java/loolu/loolu_backend/security/JpaConfig.java /workspace/app/src/main/java/loolu/loolu_backend/security/JpaConfig.java

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "loolu.loolu_backend.LooLuBackEndApplication"]
