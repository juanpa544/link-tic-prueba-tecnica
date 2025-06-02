package co.juanpablancom.linkticprueba.productos.domain.port.query;

import java.util.List;
import java.util.Optional;

import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;

public interface ProductoQuery {
    Optional<ProductoModel> buscarPorId(long id);
    List<ProductoModel> listarProductos(int page, int size);
    long contarTotal();
}
