package com.remedios.cursoapi.infra;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition( 
		info = @Info(
				title = "Remeidos_API",
				description = "Api para controle de farmacos e contigente de farmacias hospitalares",
				summary = "API para controle de Remedios",
				termsOfService = "T&C",
				contact = @Contact(
						name = "Cauã Conceição",
						email ="CauaConceicaoDev@hotmail.com"
				),
				version = "v1"
		),
		servers = {
				@Server(
						description = "Dev", 
						url = "http://localhost:8080"	
				),
				@Server(
						description = "Test",
						url = "http://localhost:8080"
				
				)	
		},
		security = @SecurityRequirement(
				name = "auth"
				
		)
)
@SecurityScheme(
		name = "auth",
		in = SecuritySchemeIn.HEADER,
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer",
		description = "security desc")
public class SwaggerConfig  {

	
}
