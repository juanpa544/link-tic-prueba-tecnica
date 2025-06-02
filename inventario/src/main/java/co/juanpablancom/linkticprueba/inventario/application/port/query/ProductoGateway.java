package co.juanpablancom.linkticprueba.inventario.application.port.query;

import co.juanpablancom.linkticprueba.inventario.application.dto.ProductoResponse;

public interface ProductoGateway {
    ProductoResponse obtenerProductoPorId(long productoId);
}
