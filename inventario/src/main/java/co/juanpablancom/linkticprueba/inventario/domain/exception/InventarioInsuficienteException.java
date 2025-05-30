package co.juanpablancom.linkticprueba.inventario.domain.exception;

public class InventarioInsuficienteException extends RuntimeException{
    public InventarioInsuficienteException(String nombreProducto, long cantidadActual, long cantidadReducir) {
        super("Solo hay " + cantidadActual + " unidades del producto "+ nombreProducto + ", no es posible comprar "+cantidadReducir);
    }
}
