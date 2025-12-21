@Bean
public OpenAPI examSeatingOpenAPI() {   // <-- rename from customOpenAPI
    final String securitySchemeName = "bearerAuth";
    return new OpenAPI()
            .info(new Info().title("Exam Seating API").version("1.0"))
            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
            .components(new Components()
                    .addSecuritySchemes(securitySchemeName,
                            new SecurityScheme()
                                    .name(securitySchemeName)
                                    .type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWT")
                    )
            );
}
