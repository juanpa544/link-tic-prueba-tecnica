package co.juanpablancom.linkticprueba.productos.domain.port.command;

import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;

public interface ProductoCommand {

    ProductoModel crear(ProductoModel productoModel);

	ProductoModel actualizar(ProductoModel productoModel);

	Long eliminar(long id);
    
}