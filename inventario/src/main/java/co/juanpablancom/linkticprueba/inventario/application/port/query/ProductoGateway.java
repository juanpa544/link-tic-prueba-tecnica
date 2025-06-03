package co.juanpablancom.linkticprueba.inventario.application.port.query;

import co.juanpablancom.linkticprueba.inventario.infrastructure.web.controller.jsonapi.JsonApiProductoResponse;

public interface ProductoGateway {
    JsonApiProductoResponse obtenerProductoPorId(long productoId);
}
