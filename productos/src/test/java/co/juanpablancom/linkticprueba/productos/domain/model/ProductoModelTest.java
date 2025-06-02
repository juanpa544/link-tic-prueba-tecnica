package co.juanpablancom.linkticprueba.productos.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import co.juanpablancom.linkticprueba.productos.domain.exception.PrecioInvalidoException;
import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoSinNombreException;

public class ProductoModelTest {

    @Test
    void debeCrearProductoCorrectamente() {
        // Act
        ProductoModel producto = new ProductoModel( 123,"Producto válido", 100.0);

        // Assert
        assertEquals( 123, producto.getId());
        assertEquals("Producto válido", producto.getNombre());
        assertEquals(100.0, producto.getPrecio());
    }

    @Test
    void debeLanzarExcepcionSiNombreEsNuloOVacio() {
        assertThrows(ProductoSinNombreException.class, () -> {
            new ProductoModel("   ", 100.0);
        });

        assertThrows(ProductoSinNombreException.class, () -> {
            new ProductoModel(null, 100.0);
        });
    }

    @Test
    void debeLanzarExcepcionSiPrecioEsNegativo() {
        assertThrows(PrecioInvalidoException.class, () -> {
            new ProductoModel("Producto", -5.0);
        });
    }
}
