package tr.gov.gib.sposodeme.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sanal Pos Service")
                        .version("1.0")
                        .description("SPos service ile ilgili islemleri yapan servistir.")
                        .termsOfService("https://gib.gov.tr")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("")
                        )
                        .contact(new Contact()
                                .email("rumeysa.incebay@gelirler.gov.tr")
                                .name("Geliştirici")
                                .url("rumeysa.incebay@gelirler.gov.tr")
                        )
                );
    }
}
