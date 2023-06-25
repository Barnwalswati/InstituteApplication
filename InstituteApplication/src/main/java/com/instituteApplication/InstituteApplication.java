package com.instituteApplication;

import java.util.Arrays;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebSecurity
@EnableSwagger2
@Configuration
@EnableWebMvc //for swagger
public class InstituteApplication {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static void main(String[] args) {
		SpringApplication.run(InstituteApplication.class, args);
	}

	private ApiKey apiKey(){
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "Bearer");
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey())).select()
				.apis(RequestHandlerSelectors.basePackage("com.instituteApplication"))
				.paths(PathSelectors.any()).build().apiInfo(myApiInfo());
	}

	private SecurityContext securityContext(){
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth(){
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	private ApiInfo myApiInfo() {
		return new ApiInfoBuilder().title("Institute Application API Information")
				.description("My Application API info")
				.contact(new Contact("Swati Vernwal", "https:www.linkedin.com/in/swati-vernwal-1a50051a9", "swati.vernwal@astegic.in"))
				.license("Full-Time")
				.licenseUrl("NA")
				.build();
	}

	//http://localhost:8080/swagger-ui/index.html

}
