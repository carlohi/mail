package cl.minsal.mail.conf;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("RestAPI").select()
				.apis(RequestHandlerSelectors.basePackage(
		                "cl.minsal.mail.controller"))  
					.paths(PathSelectors.any())
		            .build().useDefaultResponseMessages(false).apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Minsal API REST")
				.description("REST API Minsal")
				.version("0.1")
				.contact(new Contact("Jose Miguel", "/mail/v2/api-docs?group=RestAPI", "jvasquez@altiuz.com"))
				.build();
	}
}