package TFG;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"TFG", "model", "security", "config"})
@EnableJpaRepositories(basePackages = "model.repository")
@EntityScan(basePackages = "model.entities")
public class TfgApplication {
	public static void main(String[] args) {
		SpringApplication.run(TfgApplication.class, args);
	}
}