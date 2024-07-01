package loolu.loolu_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "loolu.loolu_backend.repositories")
public class LooLuBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(LooLuBackEndApplication.class, args);
    }

}
