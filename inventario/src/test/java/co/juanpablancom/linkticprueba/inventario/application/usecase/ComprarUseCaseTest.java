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

import co.juanpablancom.linkticprueba.inventario.application.dto.ConsultarInventarioResponse;
import co.juanpablancom.linkticprueba.inventario.domain.exception.InventarioInsuficienteException;
import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;

public class ComprarUseCaseTest {
private ConsultarInventarioUseCase consultarInventarioUseCase;
    private ActualizarInventarioUseCase actualizarInventarioUseCase;
    private ComprarUseCase comprarUseCase;

    @BeforeEach
    void setUp() {
        consultarInventarioUseCase = mock(ConsultarInventarioUseCase.class);
        actualizarInventarioUseCase = mock(ActualizarInventarioUseCase.class);
        comprarUseCase = new ComprarUseCase(consultarInventarioUseCase, actualizarInventarioUseCase);
    }

    @Test
    void debeReducirInventarioCorrectamente() {
        // Arrange
        String productoId = "PROD-001";
        long cantidadDisponible = 10;
        long cantidadReducir = 3;
        long nuevaCantidad = 7;
        double precio = 2000;

        ConsultarInventarioResponse respuesta = new ConsultarInventarioResponse(productoId, "Café", precio, cantidadDisponible);
        InventarioModel esperado = new InventarioModel(productoId, nuevaCantidad);

        when(consultarInventarioUseCase.ejecutar(productoId)).thenReturn(respuesta);
        when(actualizarInventarioUseCase.ejecutar(productoId, nuevaCantidad)).thenReturn(esperado);

        // Act
        InventarioModel resultado = comprarUseCase.ejecutar(productoId, cantidadReducir);

        // Assert
        assertNotNull(resultado);
        assertEquals(productoId, resultado.getProductoId());
        assertEquals(nuevaCantidad, resultado.getCantidad());

        verify(consultarInventarioUseCase).ejecutar(productoId);
        verify(actualizarInventarioUseCase).ejecutar(productoId, nuevaCantidad);
    }

    @Test
    void debeLanzarExcepcionSiCantidadReducirEsMenorOIgualACero() {
        String productoId = "PROD-002";

        assertThrows(IllegalArgumentException.class, () -> {
            comprarUseCase.ejecutar(productoId, 0);
        });

        verify(consultarInventarioUseCase, never()).ejecutar(any());
        verify(actualizarInventarioUseCase, never()).ejecutar(any(), anyLong());
    }

    @Test
    void debeLanzarExcepcionSiInventarioInsuficiente() {
        // Arrange
        String productoId = "PROD-003";
        long disponible = 5;
        long aReducir = 10;

        ConsultarInventarioResponse respuesta = new ConsultarInventarioResponse(productoId, "Azúcar", 2000, disponible);
        when(consultarInventarioUseCase.ejecutar(productoId)).thenReturn(respuesta);

        // Act & Assert
        InventarioInsuficienteException ex = assertThrows(
            InventarioInsuficienteException.class,
            () -> comprarUseCase.ejecutar(productoId, aReducir)
        );

        assertTrue(ex.getMessage().contains("Azúcar"));
        assertTrue(ex.getMessage().contains("5 unidades"));
        assertTrue(ex.getMessage().contains("comprar 10"));

        verify(consultarInventarioUseCase).ejecutar(productoId);
        verify(actualizarInventarioUseCase, never()).ejecutar(any(), anyLong());
    }
}
