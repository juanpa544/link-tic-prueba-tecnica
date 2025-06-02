package co.juanpablancom.linkticprueba.inventario.application.usecase;

import co.juanpablancom.linkticprueba.inventario.application.dto.ConsultarInventarioResponse;
import co.juanpablancom.linkticprueba.inventario.domain.exception.InventarioInsuficienteException;
import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComprarUseCaseTest {

    @Mock
    private ConsultarInventarioUseCase consultarInventarioUseCase;

    @Mock
    private ActualizarInventarioUseCase actualizarInventarioUseCase;

    @InjectMocks
    private ComprarUseCase comprarUseCase;

    @Test
    void debeLanzarExcepcionSiCantidadReducirEsMenorOIgualACero() {
        long productoId = 1;
        long cantidadReducir = 0;

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> comprarUseCase.ejecutar(productoId, cantidadReducir)
        );

        assertEquals("La cantidad a reducir debe ser mayor que cero.", exception.getMessage());
        verifyNoInteractions(consultarInventarioUseCase, actualizarInventarioUseCase);
    }

    @Test
    void debeLanzarExcepcionSiInventarioEsInsuficiente() {
        long productoId = 123;
        long cantidadReducir = 600;

        ConsultarInventarioResponse inventario = new ConsultarInventarioResponse(
                productoId,
                "Producto Prueba",
                5000,
                500
        );

        when(consultarInventarioUseCase.ejecutar(productoId)).thenReturn(inventario);

        InventarioInsuficienteException ex = assertThrows(
            InventarioInsuficienteException.class,
            () -> comprarUseCase.ejecutar(productoId, cantidadReducir)
        );

        assertTrue(ex.getMessage().contains("Producto Prueba"));

        verify(consultarInventarioUseCase).ejecutar(productoId);
        verifyNoInteractions(actualizarInventarioUseCase);
    }

    @Test
    void debeActualizarInventarioSiHayCantidadSuficiente() {
        long productoId = 123;
        long cantidadReducir = 5;

        ConsultarInventarioResponse inventario = new ConsultarInventarioResponse(
                productoId,
                "Producto Prueba",
                100,
                100
        );

        InventarioModel inventarioActualizado = new InventarioModel(productoId, 95);

        when(consultarInventarioUseCase.ejecutar(productoId)).thenReturn(inventario);
        when(actualizarInventarioUseCase.ejecutar(productoId, 95)).thenReturn(inventarioActualizado);

        InventarioModel resultado = comprarUseCase.ejecutar(productoId, cantidadReducir);

        assertEquals(95, resultado.getCantidad());
        verify(consultarInventarioUseCase).ejecutar(productoId);
        verify(actualizarInventarioUseCase).ejecutar(productoId, 95);
    }
}
