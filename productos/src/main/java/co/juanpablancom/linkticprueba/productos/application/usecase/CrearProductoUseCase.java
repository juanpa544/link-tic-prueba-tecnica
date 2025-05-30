package co.juanpablancom.linkticprueba.productos.application.usecase;

import java.util.UUID;

import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoDuplicadoException;
import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.command.ProductoCommand;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class CrearProductoUseCase {

    private final ProductoCommand productoCommand;
    private final ProductoQuery productoQuery;

    public ProductoModel ejecutar(String nombre, double precio) {
        String id = UUID.randomUUID().toString();
        if (productoQuery.buscarPorId(id).isPresent()) {
            throw new ProductoDuplicadoException(id);
        }
        ProductoModel producto = new ProductoModel(id, nombre, precio);
        return productoCommand.crear(producto);
    }
}
