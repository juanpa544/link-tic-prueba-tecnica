package co.juanpablancom.linkticprueba.productos.application.usecase;

import java.util.List;

import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ListarProductosUseCase {
    private final ProductoQuery productoQuery;

    public List<ProductoModel> ejecutar(int page, int size) {
        return productoQuery.listarProductos(page, size);
    }
}
