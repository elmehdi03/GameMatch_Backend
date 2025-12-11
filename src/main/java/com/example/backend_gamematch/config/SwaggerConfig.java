package com.example.backend_gamematch.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI gameMatchOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GameMatch API")
                        .version("1.0")
                        .description("Backend API for GameMatch (gamer matching app)"));
    }
}
