package co.juanpablancom.linkticprueba.inventario.domain.exception;

public class InventarioNoEncontradoException extends RuntimeException{
    public InventarioNoEncontradoException(String id) {
        super("No se encontró inventario para el producto con id: " + id);
    }
}
