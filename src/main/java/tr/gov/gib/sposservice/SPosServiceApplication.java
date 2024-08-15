package tr.gov.gib.sposservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "tr.gov.gib")
public class SPosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SPosServiceApplication.class, args);
	}
}
