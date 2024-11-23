package NoCountry.Fineazily.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI fimaOpenAPI() {
    return new OpenAPI()
            .info(new Info().title("Fineazily"))
            .components(new Components()
                    .addSecuritySchemes("bearer-key",
                            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
  }

  public SecurityScheme apiKeySecuritySchema() {
    return new SecurityScheme()
            .name("authorization")
            .description("Description about the TOKEN")
            .in(SecurityScheme.In.HEADER)
            .type(SecurityScheme.Type.APIKEY);
  }
}
