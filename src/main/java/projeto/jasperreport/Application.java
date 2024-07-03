package projeto.jasperreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * Spring Boot application starter class
 */
@EntityScan(basePackages = "projeto.model")
@ComponentScan(basePackages = "projeto.*")
@EnableJpaRepositories(basePackages = "projeto.repository")
@EnableTransactionManagement
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
