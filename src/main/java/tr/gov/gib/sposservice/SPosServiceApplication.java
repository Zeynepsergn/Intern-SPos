package tr.gov.gib.sposservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "tr.gov.gib")
@OpenAPIDefinition(info =
@Info(
		title = "Sanalpos Service API",
		version = "1.0",
		description = "Documentation sanalpos Service API v1.0",
		contact = @Contact(
				name = "Rumeysa İncebay",
				email = "rumeysa.incebay@gelirler.gov.tr",
				url = "https://www.gib.gov.tr/"
		),
		license = @License(
				url = "https://www.gib.gov.tr/",
				name = "Gelir İdaresi Başkanlığı Lisanslıdır"
		)
)
)
public class SPosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SPosServiceApplication.class, args);
	}
}
