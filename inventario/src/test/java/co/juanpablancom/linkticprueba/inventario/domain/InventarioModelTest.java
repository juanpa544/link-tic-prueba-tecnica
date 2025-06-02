package co.juanpablancom.linkticprueba.inventario.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import co.juanpablancom.linkticprueba.inventario.domain.exception.CantidadInvalidaException;
import co.juanpablancom.linkticprueba.inventario.domain.exception.ProductoIdInvalidoException;
import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;

public class InventarioModelTest {
    @Test
    void debeCrearInventarioCorrectamente() {
        long productoId =  123;
        long cantidad =  4;

        InventarioModel inventario = new InventarioModel(productoId, cantidad);

        assertEquals(productoId, inventario.getProductoId());
        assertEquals(cantidad, inventario.getCantidad());
    }

    @Test
    void debeLanzarExcepcionSiProductoIdEsNegativo() {
        assertThrows(ProductoIdInvalidoException.class, () -> {
            new InventarioModel(-5,  5);
        });
    }

    @Test
    void debeLanzarExcepcionSiCantidadEsNegativa() {
        assertThrows(CantidadInvalidaException.class, () -> {
            new InventarioModel( 123,  -10);
        });
    }
}
