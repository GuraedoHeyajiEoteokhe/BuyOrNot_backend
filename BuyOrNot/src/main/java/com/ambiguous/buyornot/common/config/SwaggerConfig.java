package com.ambiguous.buyornot.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        String jwtSchemeName = "jwtAuth";

        return new OpenAPI()
                .info(new Info().title("BuyOrNot API").version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList(jwtSchemeName))
                .components(new Components()
                        .addSecuritySchemes(jwtSchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .name("Authorization")
                                        .in(SecurityScheme.In.HEADER)
                        ));
    }

    @Bean
    public GroupedOpenApi stockGroup() {
        return GroupedOpenApi.builder()
                .group("Stock API")
                .pathsToMatch("/api/v1/stocks/**")
                .build();
    }

    @Bean
    public GroupedOpenApi chartsGroup() {
        return GroupedOpenApi.builder()
                .group("Charts API")
                .pathsToMatch("/api/v1/chart/**")
                .build();
    }

    @Bean
    public GroupedOpenApi chattingGroup() {
        return GroupedOpenApi.builder()
                .group("Chatting API")
                .pathsToMatch("/api/v1/chatting/**")
                .build();
    }

    @Bean
    public GroupedOpenApi hotpostingGroup() {
        return GroupedOpenApi.builder()
                .group("HotPosting API")
                .pathsToMatch("/api/v1/hotposting/**")
                .build();
    }

    @Bean
    public GroupedOpenApi userGroup() {
        return GroupedOpenApi.builder()
                .group("User API")
                .pathsToMatch("/api/v1/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi postGroup() {
        return GroupedOpenApi.builder()
                .group("Post API")
                .pathsToMatch("/api/v1/post/**")
                .build();
    }

    @Bean
    public GroupedOpenApi allGroup() {
        return GroupedOpenApi.builder()
                .group("전체 API")
                .pathsToMatch("/**")
                .build();
    }

}