package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInventarioOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Microservicio de Productos")
                        .version("1.0")
                        .description("Documentación de endpoints del microservicio de productos"));
    }
}
