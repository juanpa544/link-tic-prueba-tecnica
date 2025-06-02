package co.juanpablancom.linkticprueba.productos.application.usecase;

import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoNotFoundException;
import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.command.ProductoCommand;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ActualizarProductoUseCase {

    private final ProductoCommand productoCommand;
    private final ProductoQuery productoQuery;

    public ProductoModel ejecutar(long id, String nombre, double precio) {
        if(productoQuery.buscarPorId(id).isPresent()){
            ProductoModel actualizado = new ProductoModel(id, nombre, precio);
            return productoCommand.actualizar(actualizado);
        }else{
            throw new ProductoNotFoundException(id);
        }
    }
}
