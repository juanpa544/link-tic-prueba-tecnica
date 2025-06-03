package co.juanpablancom.linkticprueba.inventario.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.juanpablancom.linkticprueba.inventario.application.dto.ProductoAttributes;
import co.juanpablancom.linkticprueba.inventario.application.port.query.ProductoGateway;
import co.juanpablancom.linkticprueba.inventario.domain.exception.ProductoExternoNoEncontradoException;
import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import co.juanpablancom.linkticprueba.inventario.domain.port.command.InventarioCommand;
import co.juanpablancom.linkticprueba.inventario.infrastructure.web.controller.jsonapi.JsonApiProductoResponse;

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
        long productoId = 123;
        long nuevaCantidad = 20;

        // Mock del producto en formato JSON:API
        ProductoAttributes attributes = new ProductoAttributes("Nombre", 123.45);
        JsonApiProductoResponse productoJsonApi = new JsonApiProductoResponse("producto", String.valueOf(productoId), attributes);

        when(productoGateway.obtenerProductoPorId(productoId)).thenReturn(productoJsonApi);
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
        long productoId = 123;
        long cantidad = 5;

        when(productoGateway.obtenerProductoPorId(productoId))
            .thenThrow(new ProductoExternoNoEncontradoException(productoId));

        // Act & Assert
        ProductoExternoNoEncontradoException ex = assertThrows(
            ProductoExternoNoEncontradoException.class,
            () -> useCase.ejecutar(productoId, cantidad)
        );

        assertTrue(ex.getMessage().contains(String.valueOf(productoId)));
        verify(productoGateway).obtenerProductoPorId(productoId);
    }
}
