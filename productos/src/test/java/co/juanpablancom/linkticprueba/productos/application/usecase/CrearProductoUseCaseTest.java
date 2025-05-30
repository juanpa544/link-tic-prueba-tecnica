package co.juanpablancom.linkticprueba.productos.application.usecase;

import co.juanpablancom.linkticprueba.productos.domain.exception.PrecioInvalidoException;
import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoDuplicadoException;
import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoSinNombreException;
import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.command.ProductoCommand;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CrearProductoUseCaseTest {

    private ProductoCommand productoCommand;
    private ProductoQuery productoQuery;
    private CrearProductoUseCase crearProductoUseCase;

    @BeforeEach
    void setUp() {
        productoCommand = mock(ProductoCommand.class);
        productoQuery = mock(ProductoQuery.class);
        crearProductoUseCase = new CrearProductoUseCase(productoCommand, productoQuery);
    }

    @Test
    void debeCrearProductoExitosamenteConIdGenerado() {
        // Arrange
        String nombre = "Producto Nuevo";
        double precio = 100.0;

        // Simular que el ID generado no existe aún
        when(productoQuery.buscarPorId(anyString())).thenReturn(Optional.empty());
        when(productoCommand.crear(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ProductoModel creado = crearProductoUseCase.ejecutar(nombre, precio);

        // Assert
        assertNotNull(creado.getId());
        assertEquals(nombre, creado.getNombre());
        assertEquals(precio, creado.getPrecio());

        verify(productoQuery).buscarPorId(creado.getId());
        verify(productoCommand).crear(any(ProductoModel.class));
    }

    @Test
    void debeLanzarExcepcionSiIdYaExiste() {
        // Arrange
        String nombre = "Producto Repetido";
        double precio = 99.99;
        String idGenerado = UUID.randomUUID().toString();

        // Simula que el ID ya existe en base de datos
        when(productoQuery.buscarPorId(anyString()))
            .thenReturn(Optional.of(new ProductoModel(idGenerado, nombre, precio)));

        // Act & Assert
        ProductoDuplicadoException ex = assertThrows(
            ProductoDuplicadoException.class,
            () -> crearProductoUseCase.ejecutar(nombre, precio)
        );

        assertTrue(ex.getMessage().contains("Ya existe"));
        verify(productoQuery).buscarPorId(anyString());
        verify(productoCommand, never()).crear(any());
    }

    @Test
    void debeLanzarExcepcionSiNombreEsInvalido() {
        assertThrows(ProductoSinNombreException.class, () -> {
            crearProductoUseCase.ejecutar("   ", 100.0);
        });
    }

    @Test
    void debeLanzarExcepcionSiPrecioEsInvalido() {
        assertThrows(PrecioInvalidoException.class, () -> {
            crearProductoUseCase.ejecutar("Producto", -5.0);
        });
    }
}
