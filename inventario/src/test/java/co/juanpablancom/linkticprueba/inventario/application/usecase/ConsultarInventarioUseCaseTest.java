package co.juanpablancom.linkticprueba.inventario.application.usecase;

import co.juanpablancom.linkticprueba.inventario.application.dto.ConsultarInventarioResponse;
import co.juanpablancom.linkticprueba.inventario.application.dto.ProductoResponse;
import co.juanpablancom.linkticprueba.inventario.application.port.query.ProductoGateway;
import co.juanpablancom.linkticprueba.inventario.domain.exception.InventarioNoEncontradoException;
import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import co.juanpablancom.linkticprueba.inventario.domain.port.query.InventarioQuery;
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
        String productoId = "PROD-001";
        InventarioModel inventario = new InventarioModel(productoId, 20);
        ProductoResponse producto = new ProductoResponse(productoId, "Producto X", 50.0);

        when(inventarioQuery.obtenerPorProductoId(productoId)).thenReturn(Optional.of(inventario));
        when(productoGateway.obtenerProductoPorId(productoId)).thenReturn(producto);

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
        String productoId = "PROD-002";
        when(inventarioQuery.obtenerPorProductoId(productoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InventarioNoEncontradoException.class, () -> {
            consultarInventarioUseCase.ejecutar(productoId);
        });

        verify(inventarioQuery).obtenerPorProductoId(productoId);
        verify(productoGateway, never()).obtenerProductoPorId(any());
    }
}
