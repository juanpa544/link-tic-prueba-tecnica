package co.juanpablancom.linkticprueba.productos.domain.exception;

public class PaginaInvalidaException extends RuntimeException {
    public PaginaInvalidaException(int pagina) {
        super("El número de página debe ser mayor que cero. Valor recibido: " + pagina);
    }
}
