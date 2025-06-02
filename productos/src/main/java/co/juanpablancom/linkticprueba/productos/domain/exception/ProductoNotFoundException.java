package co.juanpablancom.linkticprueba.productos.domain.exception;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(long id) {
        super("No se encontró un producto con el ID: " + id);
    }
}
