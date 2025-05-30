package co.juanpablancom.linkticprueba.productos.application.usecase;

import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoNotFoundException;
import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BuscarProductoPorIdUseCase {
    private final ProductoQuery productoQuery;

    public ProductoModel ejecutar(String id) {
        return productoQuery.buscarPorId(id)
            .orElseThrow(() -> new ProductoNotFoundException(id));
    }
}
