package loolu.loolu_backend.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages = "loolu.loolu_backend.repositories")
public class JpaConfig {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "jpaSharedEM_entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("loolu.loolu_backend.entities")
                .persistenceUnit("jpaSharedEM")
                .build();
    }
}
