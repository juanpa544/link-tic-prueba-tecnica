package co.juanpablancom.linkticprueba.inventario.application.usecase;

import co.juanpablancom.linkticprueba.inventario.application.dto.ConsultarInventarioResponse;
import co.juanpablancom.linkticprueba.inventario.application.dto.ProductoAttributes;
import co.juanpablancom.linkticprueba.inventario.application.port.query.ProductoGateway;
import co.juanpablancom.linkticprueba.inventario.domain.exception.InventarioNoEncontradoException;
import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import co.juanpablancom.linkticprueba.inventario.domain.port.query.InventarioQuery;
import co.juanpablancom.linkticprueba.inventario.infrastructure.web.controller.jsonapi.JsonApiProductoResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsultarInventarioUseCaseTest {

    private InventarioQuery inventarioQuery;
    private ProductoGateway productoGateway;
    private ConsultarInventarioUseCase consultarInventarioUseCase;

    @BeforeEach
    void setUp() {
        inventarioQuery = mock(InventarioQuery.class);
        productoGateway = mock(ProductoGateway.class);
        consultarInventarioUseCase = new ConsultarInventarioUseCase(inventarioQuery, productoGateway);
    }

    @Test
    void debeRetornarInventarioConDatosDelProducto() {
        // Arrange
        long productoId = 123;
        InventarioModel inventario = new InventarioModel(productoId, 20);

        ProductoAttributes attributes = new ProductoAttributes("Producto X", 50.0);
        JsonApiProductoResponse productoJsonApi = new JsonApiProductoResponse("producto", String.valueOf(productoId), attributes);

        when(inventarioQuery.obtenerPorProductoId(productoId)).thenReturn(Optional.of(inventario));
        when(productoGateway.obtenerProductoPorId(productoId)).thenReturn(productoJsonApi);

        // Act
        ConsultarInventarioResponse resultado = consultarInventarioUseCase.ejecutar(productoId);

        // Assert
        assertNotNull(resultado);
        assertEquals(productoId, resultado.getProductoId());
        assertEquals("Producto X", resultado.getNombreProducto());
        assertEquals(50.0, resultado.getPrecio());
        assertEquals(20, resultado.getCantidadDisponible());

        verify(inventarioQuery).obtenerPorProductoId(productoId);
        verify(productoGateway).obtenerProductoPorId(productoId);
    }

    @Test
    void debeLanzarExcepcionSiNoSeEncuentraInventario() {
        // Arrange
        long productoId = 123;
        when(inventarioQuery.obtenerPorProductoId(productoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InventarioNoEncontradoException.class, () -> {
            consultarInventarioUseCase.ejecutar(productoId);
        });

        verify(inventarioQuery).obtenerPorProductoId(productoId);
    }
}
