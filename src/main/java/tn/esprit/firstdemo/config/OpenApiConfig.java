package tn.esprit.firstdemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Application Gestion Foyer",
                version = "1.0.0",
                description = "Platform de gestion des foyers universitaires, couvrant les foyers, blocs, chambres, étudiants, réservations et universités.\n\n**Équipe ASII**\n- Site web : https://equipe-asii.tn",
                termsOfService = "https://equipe-asii.tn/terms",
                contact = @Contact(
                        name = "Equipe ASII",
                        url = "https://equipe-asii.tn",
                        email = "chernisamar@gmail.com"
                )
        )
)
@Configuration
public class OpenApiConfig {
}
