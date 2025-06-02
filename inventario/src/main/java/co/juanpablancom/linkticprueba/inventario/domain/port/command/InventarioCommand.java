package co.juanpablancom.linkticprueba.inventario.domain.port.command;

import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;

public interface InventarioCommand {

    InventarioModel actualizarCantidad(long productoId, long nuevaCantidad);
    
}
