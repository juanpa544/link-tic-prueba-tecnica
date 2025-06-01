package co.juanpablancom.linkticprueba.productos.domain.exception;

public class ProductoDuplicadoException extends RuntimeException {

    public ProductoDuplicadoException(Long id) {
        super("Ya existe un producto con el id: " + id);
    }
}
