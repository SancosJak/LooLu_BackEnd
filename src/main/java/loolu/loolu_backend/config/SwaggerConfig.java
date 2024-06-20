package loolu.loolu_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@OpenAPIDefinition(
        info = @Info(
                title = "Loolu Service Backend",
                description = "Loolu Backend API application for JSON web tokens",
                version = "1.0.0",
                contact = @Contact(
                        name = "Sandor Ivanyo",
                        email = "iskander.sancosjak@gmail.com",
                        url = "https://github.com/SancosJak"
                )
        )
)
public class SwaggerConfig {

    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("loolu.loolu_backend.controllers"))
                .paths(PathSelectors.any())
                .build();


    }
}