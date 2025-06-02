package co.juanpablancom.linkticprueba.inventario.domain.model;

import co.juanpablancom.linkticprueba.inventario.domain.exception.CantidadInvalidaException;
import co.juanpablancom.linkticprueba.inventario.domain.exception.ProductoIdInvalidoException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventarioModel {
    private long productoId;
    private long cantidad;

    public InventarioModel(long productoId, long cantidad) {
        if (productoId < 0) {
            throw new ProductoIdInvalidoException();
        }
        if (cantidad <= 0) {
            throw new CantidadInvalidaException(cantidad);
        }

        this.productoId = productoId;
        this.cantidad = cantidad;
    }
}
