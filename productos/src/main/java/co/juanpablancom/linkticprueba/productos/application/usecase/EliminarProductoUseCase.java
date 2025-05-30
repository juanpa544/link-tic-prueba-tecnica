package co.juanpablancom.linkticprueba.productos.application.usecase;

import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoNotFoundException;
import co.juanpablancom.linkticprueba.productos.domain.port.command.ProductoCommand;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class EliminarProductoUseCase {

    private final ProductoCommand productoCommand;
    private final ProductoQuery productoQuery;

    public String ejecutar(String id) {
        if(productoQuery.buscarPorId(id).isPresent()){
            return productoCommand.eliminar(id);
        } else { 
            throw new ProductoNotFoundException(id);
        }
    }
}
