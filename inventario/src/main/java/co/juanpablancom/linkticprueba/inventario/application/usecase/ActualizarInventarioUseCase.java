package co.juanpablancom.linkticprueba.inventario.application.usecase;

import org.springframework.stereotype.Service;

import co.juanpablancom.linkticprueba.inventario.application.port.query.ProductoGateway;
import co.juanpablancom.linkticprueba.inventario.domain.exception.ProductoExternoNoEncontradoException;
import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import co.juanpablancom.linkticprueba.inventario.domain.port.command.InventarioCommand;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActualizarInventarioUseCase {

    private final InventarioCommand inventarioCommand;
    private final ProductoGateway productoGateway;

    public InventarioModel ejecutar(String productoId, long nuevaCantidad) {

        // Validar que el producto exista en el microservicio de productos
        try {
            productoGateway.obtenerProductoPorId(productoId);
        } catch (ProductoExternoNoEncontradoException e) {
            throw new ProductoExternoNoEncontradoException(productoId);
        }

        InventarioModel nuevoInventario = inventarioCommand.actualizarCantidad(productoId, nuevaCantidad);
        System.out.println("!!! Inventario actualizado → productoId: " + productoId + ", cantidad: " + nuevaCantidad + " !!!");
        return nuevoInventario;
    }
}