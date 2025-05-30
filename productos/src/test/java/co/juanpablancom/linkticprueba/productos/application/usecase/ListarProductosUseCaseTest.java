package co.juanpablancom.linkticprueba.productos.application.usecase;

import co.juanpablancom.linkticprueba.productos.domain.exception.PaginaInvalidaException;
import co.juanpablancom.linkticprueba.productos.domain.exception.SizeInvalidoException;
import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarProductosUseCaseTest {

    private ProductoQuery productoQuery;
    private ListarProductosUseCase listarProductosUseCase;

    @BeforeEach
    void setUp() {
        productoQuery = mock(ProductoQuery.class);
        listarProductosUseCase = new ListarProductosUseCase(productoQuery);
    }

    @Test
    void debeListarProductosConPaginacion() {
        int page = 0;
        int size = 2;

        List<ProductoModel> productosSimulados = List.of(
            new ProductoModel("id1", "Producto 1", 10.0),
            new ProductoModel("id2", "Producto 2", 20.0)
        );

        when(productoQuery.listarProductos(page, size)).thenReturn(productosSimulados);

        List<ProductoModel> resultado = listarProductosUseCase.ejecutar(page, size);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(productoQuery).listarProductos(page, size);
    }

    @Test
    void debeRetornarListaVaciaSiNoHayProductos() {
        when(productoQuery.listarProductos(0, 5)).thenReturn(List.of());

        List<ProductoModel> resultado = listarProductosUseCase.ejecutar(0, 5);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(productoQuery).listarProductos(0, 5);
    }

    @Test
    void debeLanzarExcepcionSiPaginaEsInvalida() {
        assertThrows(PaginaInvalidaException.class, () -> {
            listarProductosUseCase.ejecutar(-1, 10);
        });
    }

    @Test
    void debeLanzarExcepcionSiSizeEsInvalido() {
        assertThrows(SizeInvalidoException.class, () -> {
            listarProductosUseCase.ejecutar(0, -5);
        });
    }
}
