package co.juanpablancom.linkticprueba.productos.application.usecase;

import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoNotFoundException;
import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarProductoPorIdUseCaseTest {

    private ProductoQuery productoQuery;
    private BuscarProductoPorIdUseCase buscarProductoPorIdUseCase;

    @BeforeEach
    void setUp() {
        productoQuery = mock(ProductoQuery.class);
        buscarProductoPorIdUseCase = new BuscarProductoPorIdUseCase(productoQuery);
    }

    @Test
    void debeRetornarProductoCuandoExiste() {
        // Arrange
        Long id = (long) 1000;
        ProductoModel producto = new ProductoModel(id, "Producto de prueba", 100.0);
        when(productoQuery.buscarPorId(id)).thenReturn(Optional.of(producto));

        // Act
        ProductoModel resultado = buscarProductoPorIdUseCase.ejecutar(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Producto de prueba", resultado.getNombre());
        assertEquals(100.0, resultado.getPrecio());
        verify(productoQuery).buscarPorId(id);
    }

    @Test
    void debeLanzarExcepcionCuandoProductoNoExiste() {
        // Arrange
        Long id = (long) 5000;
        when(productoQuery.buscarPorId(id)).thenReturn(Optional.empty());

        // Act & Assert
        ProductoNotFoundException exception = assertThrows(ProductoNotFoundException.class, () -> {
            buscarProductoPorIdUseCase.ejecutar(id);
        });

        assertTrue(exception.getMessage().contains(id.toString()));
        verify(productoQuery).buscarPorId(id);
    }
}

