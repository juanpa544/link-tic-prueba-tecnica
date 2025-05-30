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

class EliminarProductoUseCaseTest {

    private ProductoCommand productoCommand;
    private ProductoQuery productoQuery;
    private EliminarProductoUseCase eliminarProductoUseCase;

    @BeforeEach
    void setUp() {
        productoCommand = mock(ProductoCommand.class);
        productoQuery = mock(ProductoQuery.class);
        eliminarProductoUseCase = new EliminarProductoUseCase(productoCommand, productoQuery);
    }

    @Test
    void debeEliminarProductoExitosamente() {
        // Arrange
        String id = "producto123";
        when(productoQuery.buscarPorId(id)).thenReturn(Optional.of(new ProductoModel(id, "Producto", 100.0)));
        when(productoCommand.eliminar(id)).thenReturn("Producto eliminado: " + id);

        // Act
        String resultado = eliminarProductoUseCase.ejecutar(id);

        // Assert
        assertEquals("Producto eliminado: " + id, resultado);
        verify(productoQuery).buscarPorId(id);
        verify(productoCommand).eliminar(id);
    }

    @Test
    void debeLanzarExcepcionSiProductoNoExiste() {
        // Arrange
        String id = "noExiste123";
        when(productoQuery.buscarPorId(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProductoNotFoundException.class, () -> eliminarProductoUseCase.ejecutar(id));

        verify(productoQuery).buscarPorId(id);
        verify(productoCommand, never()).eliminar(anyString());
    }
}
