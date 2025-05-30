package co.juanpablancom.linkticprueba.inventario.application.usecase;

import org.springframework.stereotype.Service;

import co.juanpablancom.linkticprueba.inventario.domain.exception.InventarioNoEncontradoException;
import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import co.juanpablancom.linkticprueba.inventario.domain.port.command.InventarioCommand;
import co.juanpablancom.linkticprueba.inventario.domain.port.query.InventarioQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActualizarInventarioUseCase {

    private final InventarioQuery inventarioQuery;
    private final InventarioCommand inventarioCommand;

    public InventarioModel ejecutar(String productoId, long nuevaCantidad) {
        InventarioModel actual = inventarioQuery
            .obtenerPorProductoId(productoId)
            .orElseThrow(() -> new InventarioNoEncontradoException(productoId));

        actual.setCantidad(nuevaCantidad);

        InventarioModel actualizado = inventarioCommand.actualizarCantidad(productoId, nuevaCantidad);

        System.out.println("!!! Inventario actualizado → productoId: " + productoId + ", nueva cantidad: " + nuevaCantidad +" !!!");

        return actualizado;
    }
}
