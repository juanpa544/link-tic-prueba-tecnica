package co.juanpablancom.linkticprueba.inventario.application.service;

import org.springframework.stereotype.Service;

import co.juanpablancom.linkticprueba.inventario.application.dto.ActualizarInventarioRequest;
import co.juanpablancom.linkticprueba.inventario.application.dto.ConsultarInventarioResponse;
import co.juanpablancom.linkticprueba.inventario.application.usecase.ActualizarInventarioUseCase;
import co.juanpablancom.linkticprueba.inventario.application.usecase.ComprarUseCase;
import co.juanpablancom.linkticprueba.inventario.application.usecase.ConsultarInventarioUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final ConsultarInventarioUseCase consultarInventarioUseCase;
    private final ActualizarInventarioUseCase actualizarInventarioUseCase;
    private final ComprarUseCase comprarUseCase;

    public ConsultarInventarioResponse consultarInventario(long productoId) {
        return consultarInventarioUseCase.ejecutar(productoId);
    }

    public void actualizarInventario(ActualizarInventarioRequest request) {
        actualizarInventarioUseCase.ejecutar(request.getProductoId(), request.getCantidad());
    }

    public void aplicarCompra(long productoId, long cantidadComprada) {
        comprarUseCase.ejecutar(productoId, cantidadComprada);
    }
}
