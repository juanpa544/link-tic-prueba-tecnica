package co.juanpablancom.linkticprueba.inventario.domain.exception;

public class ProductoIdInvalidoException extends RuntimeException{
    public ProductoIdInvalidoException() {
        super("El inventario del producto a buscar debe tener un id válido.");
    }
}
