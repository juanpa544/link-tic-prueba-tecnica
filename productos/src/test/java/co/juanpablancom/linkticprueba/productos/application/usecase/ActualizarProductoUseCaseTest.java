package co.juanpablancom.linkticprueba.productos.application.usecase;

import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoNotFoundException;
import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.command.ProductoCommand;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActualizarProductoUseCaseTest {

    private ProductoCommand productoCommand;
    private ProductoQuery productoQuery;
    private ActualizarProductoUseCase actualizarProductoUseCase;

    @BeforeEach
    void setUp() {
        productoCommand = mock(ProductoCommand.class);
        productoQuery = mock(ProductoQuery.class);
        actualizarProductoUseCase = new ActualizarProductoUseCase(productoCommand, productoQuery);
    }

    @Test
    void debeActualizarProductoExitosamente() {
        // Arrange
        String id = "abc123";
        String nombre = "Producto Actualizado";
        double precio = 150.0;

        ProductoModel productoExistente = new ProductoModel(id, "Viejo", 100.0);
        ProductoModel productoActualizado = new ProductoModel(id, nombre, precio);

        when(productoQuery.buscarPorId(id)).thenReturn(Optional.of(productoExistente));
        when(productoCommand.actualizar(any())).thenReturn(productoActualizado);

        // Act
        ProductoModel resultado = actualizarProductoUseCase.ejecutar(id, nombre, precio);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(nombre, resultado.getNombre());
        assertEquals(precio, resultado.getPrecio());

        verify(productoQuery).buscarPorId(id);
        verify(productoCommand).actualizar(any());
    }

    @Test
    void debeLanzarProductoNotFoundExceptionSiProductoNoExiste() {
        // Arrange
        String id = "no-existe";

        // Act & Assert
        assertThrows(ProductoNotFoundException.class, () -> {
            actualizarProductoUseCase.ejecutar(id, "Nombre", 100.0);
        });

        verify(productoQuery).buscarPorId(id);
        verify(productoCommand, never()).actualizar(any());
    }
}

