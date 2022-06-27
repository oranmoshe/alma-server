package com.roe.almaserver;

import com.roe.almaserver.model.Portfolio;
import com.roe.almaserver.model.Syndicator;
import com.roe.almaserver.repository.SyndicatorRepository;
import com.roe.almaserver.services.SyndicatorService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class AlmaServerApplication {

	@Autowired
	private SyndicatorService syndicatorService;
	private final Logger logger = LoggerFactory.getLogger(AlmaServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlmaServerApplication.class, args);
	}

	@Bean
	public void init(){
		Syndicator syndicator = new Syndicator();
		syndicator.setName("test-syndicator");
		syndicator.setActivated(true);

		Portfolio portfolio1 = new Portfolio(syndicator);
		portfolio1.setName("test-portfolio1");
		portfolio1.setAmount("200,000");
		portfolio1.setCreationDate(new Timestamp(Instant.now().getEpochSecond()));
		portfolio1.setOfferingName("offering name1");
		portfolio1.setPriority("priority");
		portfolio1.setStatus("status");
		portfolio1.setCustomerName("customerName1");

		Portfolio portfolio2 = new Portfolio(syndicator);
		portfolio2.setName("test-portfolio2");
		portfolio2.setAmount("320,000");
		portfolio2.setCreationDate(new Timestamp(Instant.now().getEpochSecond()));
		portfolio2.setOfferingName("offering name2");
		portfolio2.setPriority("priority");
		portfolio2.setStatus("status");
		portfolio2.setCustomerName("customerName2");

		Portfolio portfolio3 = new Portfolio(syndicator);
		portfolio3.setName("test-portfolio");
		portfolio3.setAmount("100,000");
		portfolio3.setCreationDate(new Timestamp(Instant.now().getEpochSecond()));
		portfolio3.setOfferingName("offering name3");
		portfolio3.setPriority("priority");
		portfolio3.setStatus("status");
		portfolio3.setCustomerName("customerName3");

		syndicator.setPortfolios(new HashSet<>(Arrays.asList(portfolio1,portfolio2,portfolio3)));

		syndicatorService.createSyndicator(syndicator);
		logger.info("new syndicator has been created successfully");
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

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
