package co.juanpablancom.linkticprueba.inventario.domain.exception;

public class CantidadInvalidaException extends RuntimeException{
    public CantidadInvalidaException(long cantidad) {
        super("La cantidad del producto dentro del inventario debe ser mayor que cero. Valor recibido: " + cantidad);
    }
}
