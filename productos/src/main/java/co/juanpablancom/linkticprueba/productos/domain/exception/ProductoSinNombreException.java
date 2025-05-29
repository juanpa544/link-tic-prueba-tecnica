package co.juanpablancom.linkticprueba.productos.domain.exception;

public class ProductoSinNombreException extends RuntimeException {
    public ProductoSinNombreException() {
        super("El producto debe tener un nombre válido.");
    }
}
