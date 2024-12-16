package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"inicio/repository", "inicio/entities", "inicio/service", "inicio/controller"})
public class MicroservicioContactosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioContactosApplication.class, args);
	}

}
