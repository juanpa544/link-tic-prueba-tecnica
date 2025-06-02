package co.juanpablancom.linkticprueba.productos.application.usecase;

import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.command.ProductoCommand;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@Transactional
public class CrearProductoUseCaseTest {

    private ProductoCommand productoCommand;
    private ProductoQuery productoQuery;
    private CrearProductoUseCase crearProductoUseCase;

    @BeforeEach
    void setUp() {
        productoCommand = mock(ProductoCommand.class);
        productoQuery = mock(ProductoQuery.class);
        crearProductoUseCase = new CrearProductoUseCase(productoCommand);
    }

    @Test
    void debeCrearProductoExitosamenteConIdGenerado() {
        // Arrange
        String nombre = "Producto Nuevo";
        double precio = 100.0;

        // Simular que el ID generado no existe aún
        when(productoQuery.buscarPorId(anyLong())).thenReturn(Optional.empty());
        when(productoCommand.crear(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ProductoModel creado = crearProductoUseCase.ejecutar(nombre, precio);

        // Assert
        assertEquals(nombre, creado.getNombre());
        assertEquals(precio, creado.getPrecio());
        
    }
}
