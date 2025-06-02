package co.juanpablancom.linkticprueba.inventario.application.usecase;

import org.springframework.stereotype.Service;

import co.juanpablancom.linkticprueba.inventario.application.dto.ConsultarInventarioResponse;
import co.juanpablancom.linkticprueba.inventario.domain.exception.InventarioInsuficienteException;
import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComprarUseCase {

    private final ConsultarInventarioUseCase consultarInventarioUseCase;
    private final ActualizarInventarioUseCase actualizarInventarioUseCase;

    public InventarioModel ejecutar(long productoId, long cantidadReducir) {

        if (cantidadReducir <= 0) {
            throw new IllegalArgumentException("La cantidad a reducir debe ser mayor que cero.");
        }
        
        ConsultarInventarioResponse inventarioActual = consultarInventarioUseCase.ejecutar(productoId); // Validación externa

        if (inventarioActual.getCantidadDisponible() < cantidadReducir) {
            throw new InventarioInsuficienteException(inventarioActual.getNombreProducto(), inventarioActual.getCantidadDisponible(), cantidadReducir);
        }

        long nuevaCantidad = inventarioActual.getCantidadDisponible() - cantidadReducir;

        return actualizarInventarioUseCase.ejecutar(productoId, nuevaCantidad);
    }
}

