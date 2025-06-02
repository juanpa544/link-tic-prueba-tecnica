package co.juanpablancom.linkticprueba.inventario.domain.port.query;

import java.util.Optional;

import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;

public interface InventarioQuery {
    Optional<InventarioModel> obtenerPorProductoId(long productoId);
}
