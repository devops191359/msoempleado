package mx.com.ironbit.msoempleado.swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                description = "Awesome Resources",
                version = "V0.0.1",
                title =  "Api de Empleados",
                contact = @Contact(
                        name = "Eduardo Guillen Maldonado",
                        email = "talento@ironbit.com",
                        url = "http://www.ironbit.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = {@Server(url = "http://www.ironbit.com")}
)
public interface ApiDocumentationConfig {

}