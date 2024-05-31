package myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "myapp", exclude = { SecurityAutoConfiguration.class })
@EntityScan(basePackages = "myapp.model")
@EnableJpaRepositories(basePackages = "myapp.repository")

public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
