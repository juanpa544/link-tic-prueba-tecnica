package co.juanpablancom.linkticprueba.productos.application.usecase;

import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ContarProductosUseCase {
    private final ProductoQuery productoQuery;

    public Long ejecutar() {
        return productoQuery.contarTotal();
    }
}
