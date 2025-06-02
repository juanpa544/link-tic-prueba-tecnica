package co.juanpablancom.linkticprueba.inventario.application.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.juanpablancom.linkticprueba.inventario.application.dto.ConsultarInventarioResponse;
import co.juanpablancom.linkticprueba.inventario.application.dto.ProductoResponse;
import co.juanpablancom.linkticprueba.inventario.application.port.query.ProductoGateway;
import co.juanpablancom.linkticprueba.inventario.domain.exception.InventarioNoEncontradoException;
import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import co.juanpablancom.linkticprueba.inventario.domain.port.query.InventarioQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultarInventarioUseCase {

    private final InventarioQuery inventarioQuery;
    private final ProductoGateway productoGateway;

    public ConsultarInventarioResponse ejecutar(long productoId) {
        Optional<InventarioModel> inventario = inventarioQuery.obtenerPorProductoId(productoId);
        if(inventario.isPresent()){
            ProductoResponse producto = productoGateway.obtenerProductoPorId(productoId);
            return new ConsultarInventarioResponse(
            producto.getId(),
            producto.getNombre(),
            producto.getPrecio(),
            inventario.get().getCantidad()
        );
        }else{
            throw new InventarioNoEncontradoException(productoId);
        }
    }
}
