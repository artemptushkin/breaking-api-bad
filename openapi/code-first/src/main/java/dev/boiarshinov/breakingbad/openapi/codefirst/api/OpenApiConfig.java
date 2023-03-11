package dev.boiarshinov.breakingbad.openapi.codefirst.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    public static final String CRYSTAL_TAG = "Crystal";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Breaking Bad API")
                        .version("1.0"))
                .tags(List.of(
                        new Tag().name(CRYSTAL_TAG)
                                .description("API methods to interact with crystals")
                ));
    }
}
