package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.config;

import co.juanpablancom.linkticprueba.productos.application.usecase.*;
import co.juanpablancom.linkticprueba.productos.domain.port.command.ProductoCommand;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CrearProductoUseCase crearProductoUseCase(ProductoCommand command, ProductoQuery query) {
        return new CrearProductoUseCase(command, query);
    }

    @Bean
    public ActualizarProductoUseCase actualizarProductoUseCase(ProductoCommand command, ProductoQuery query) {
        return new ActualizarProductoUseCase(command, query);
    }

    @Bean
    public EliminarProductoUseCase eliminarProductoUseCase(ProductoCommand command, ProductoQuery query) {
        return new EliminarProductoUseCase(command, query);
    }

    @Bean
    public BuscarProductoPorIdUseCase buscarProductoPorIdUseCase(ProductoQuery query) {
        return new BuscarProductoPorIdUseCase(query);
    }

    @Bean
    public ListarProductosUseCase listarProductosUseCase(ProductoQuery query) {
        return new ListarProductosUseCase(query);
    }

    @Bean
    public ContarProductosUseCase contarProductosUseCase(ProductoQuery query) {
        return new ContarProductosUseCase(query);
    }
}

