package co.juanpablancom.linkticprueba.productos.application.usecase;

import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.command.ProductoCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class CrearProductoUseCase {

    private final ProductoCommand productoCommand;

    public ProductoModel ejecutar(String nombre, double precio) {
        ProductoModel producto = new ProductoModel(nombre, precio);
        return productoCommand.crear(producto); 
    }
}
