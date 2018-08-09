package argedor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class DocumentationConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("argedor"))
				.paths(PathSelectors.regex("/api.*")).build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {

		ApiInfo apiInfo = new ApiInfo("Yapsat REST API",
				"Yapsat is a location-based platform which Housewifes makes money in return makes food. "
						+ "You can give an order home cooking as a customer to office meeting and organizations."
						+ "API provide to add and update user,order,product",
				"v1.0", "https://medium.com/@harunkeleolu",
				new Contact("Harun KELEŞOĞLU", "https://twitter.com/keles0glu", "harun.kelesoglu19@gmail.com"),
				null, null);

		return apiInfo;
	}

}
