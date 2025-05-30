package co.juanpablancom.linkticprueba.inventario.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.juanpablancom.linkticprueba.inventario.application.dto.ProductoResponse;
import co.juanpablancom.linkticprueba.inventario.application.port.query.ProductoGateway;
import co.juanpablancom.linkticprueba.inventario.domain.exception.ProductoExternoNoEncontradoException;
import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import co.juanpablancom.linkticprueba.inventario.domain.port.command.InventarioCommand;

public class ActualizarInventarioUseCaseTest {
    private InventarioCommand inventarioCommand;
    private ProductoGateway productoGateway;
    private ActualizarInventarioUseCase useCase;

    @BeforeEach
    void setUp() {
        inventarioCommand = mock(InventarioCommand.class);
        productoGateway = mock(ProductoGateway.class);
        useCase = new ActualizarInventarioUseCase(inventarioCommand, productoGateway);
    }

    @Test
    void debeActualizarInventarioSiProductoExiste() {
        // Arrange
        String productoId = "PROD-001";
        long nuevaCantidad = 20;

        // Simula que el producto existe
        when(productoGateway.obtenerProductoPorId(productoId)).thenReturn(new ProductoResponse(productoId, "Nombre", 123.45));
        InventarioModel inventarioEsperado = new InventarioModel(productoId, nuevaCantidad);
        when(inventarioCommand.actualizarCantidad(productoId, nuevaCantidad)).thenReturn(inventarioEsperado);

        // Act
        InventarioModel resultado = useCase.ejecutar(productoId, nuevaCantidad);

        // Assert
        assertNotNull(resultado);
        assertEquals(productoId, resultado.getProductoId());
        assertEquals(nuevaCantidad, resultado.getCantidad());

        verify(productoGateway).obtenerProductoPorId(productoId);
        verify(inventarioCommand).actualizarCantidad(productoId, nuevaCantidad);
    }

    @Test
    void debeLanzarExcepcionSiProductoNoExiste() {
        // Arrange
        String productoId = "NO_EXISTE";
        long cantidad = 10;

        // Simula que el gateway lanza excepción
        when(productoGateway.obtenerProductoPorId(productoId))
            .thenThrow(new ProductoExternoNoEncontradoException(productoId));

        // Act & Assert
        ProductoExternoNoEncontradoException ex = assertThrows(
            ProductoExternoNoEncontradoException.class,
            () -> useCase.ejecutar(productoId, cantidad)
        );

        assertTrue(ex.getMessage().contains(productoId));
        verify(productoGateway).obtenerProductoPorId(productoId);
        verify(inventarioCommand, never()).actualizarCantidad(any(), anyLong());
    }
}
