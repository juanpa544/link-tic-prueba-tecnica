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
        String productoId = "PROD-001";
        long cantidad = 10;

        InventarioModel inventario = new InventarioModel(productoId, cantidad);

        assertEquals(productoId, inventario.getProductoId());
        assertEquals(cantidad, inventario.getCantidad());
    }

    @Test
    void debeLanzarExcepcionSiProductoIdEsNull() {
        assertThrows(ProductoIdInvalidoException.class, () -> {
            new InventarioModel(null, 5);
        });
    }

    @Test
    void debeLanzarExcepcionSiProductoIdEsVacio() {
        assertThrows(ProductoIdInvalidoException.class, () -> {
            new InventarioModel("   ", 5);
        });
    }

    @Test
    void debeLanzarExcepcionSiCantidadEsCero() {
        assertThrows(CantidadInvalidaException.class, () -> {
            new InventarioModel("PROD-001", 0);
        });
    }

    @Test
    void debeLanzarExcepcionSiCantidadEsNegativa() {
        assertThrows(CantidadInvalidaException.class, () -> {
            new InventarioModel("PROD-001", -10);
        });
    }
}
