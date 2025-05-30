package co.juanpablancom.linkticprueba.inventario.domain.exception;

public class ProductoExternoNoEncontradoException extends RuntimeException{
    public ProductoExternoNoEncontradoException(String productoId) {
        super("No se encontró un producto en el microservicio externo para el id: " + productoId);
    }
}
