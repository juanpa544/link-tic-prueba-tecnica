package co.juanpablancom.linkticprueba.productos.domain.exception;

public class SizeInvalidoException extends RuntimeException{
    public SizeInvalidoException(int size) {
        super("El número de size debe ser mayor que cero. Valor recibido: " + size);
    }
}
