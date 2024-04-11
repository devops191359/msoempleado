package mx.com.ironbit.msoempleado.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Clase Configuración de Swagger SwaggerConfig
 * @author Eduardo Guillen Maldonado
 * 
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	
	@Bean
	public Docket SwaggerApi() {
		 return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("mx.com.ironbit.msoempleado"))
	                .build();
	}
}