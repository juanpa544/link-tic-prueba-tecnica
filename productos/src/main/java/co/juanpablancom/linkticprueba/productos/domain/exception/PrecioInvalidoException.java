package co.juanpablancom.linkticprueba.productos.domain.exception;

public class PrecioInvalidoException extends RuntimeException {
    public PrecioInvalidoException(double precio) {
        super("El precio del producto debe ser mayor que cero. Valor recibido: " + precio);
    }
}
