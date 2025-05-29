package co.juanpablancom.linkticprueba.productos.domain.port.command;

import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;

public interface ProductoCommand {

    String crear(ProductoModel productoModel);

	String actualizar(ProductoModel productoModel);

	String eliminar(Long id);
    
}