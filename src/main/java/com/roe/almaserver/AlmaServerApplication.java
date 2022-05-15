package com.roe.almaserver;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlmaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlmaServerApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String docVersion, @Value("${project.version}") String appVersion) {
		return new OpenAPI()
				.info(new Info().title("Alma API").version(docVersion)
						.description("Hi <b>"+appVersion+"</b>"))
				.addSecurityItem(new SecurityRequirement().addList("Authorization"))
				.components(new Components().addSecuritySchemes("Authorization",
						new SecurityScheme().name("Authorization").type(SecurityScheme.Type.HTTP).scheme("bearer")));
	}
}
