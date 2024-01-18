package tr.com.metea.hotelium.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mete Aydin
 * @since 17.10.2021
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Hotelium API", version = "v1"))
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        var securitySchemeName = "bearerAuth";
        var securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .in(SecurityScheme.In.HEADER)
                .bearerFormat("JWT");

        var securityRequirement = new SecurityRequirement().addList(securitySchemeName);

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(new Components().addSecuritySchemes(securitySchemeName, securityScheme));
    }
}
