package app.husna.husnabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"app.husna.husnabackend"})
public class HusnaBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(HusnaBackendApplication.class, args);
	}
}
