package co.juanpablancom.linkticprueba.productos.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.juanpablancom.linkticprueba.productos.application.usecase.ActualizarProductoUseCase;
import co.juanpablancom.linkticprueba.productos.application.usecase.BuscarProductoPorIdUseCase;
import co.juanpablancom.linkticprueba.productos.application.usecase.ContarProductosUseCase;
import co.juanpablancom.linkticprueba.productos.application.usecase.CrearProductoUseCase;
import co.juanpablancom.linkticprueba.productos.application.usecase.EliminarProductoUseCase;
import co.juanpablancom.linkticprueba.productos.application.usecase.ListarProductosUseCase;
import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Service
@Builder
@AllArgsConstructor
public class ProductoService {
private final CrearProductoUseCase crearProductoUseCase;
    private final BuscarProductoPorIdUseCase buscarProductoPorIdUseCase;
    private final ListarProductosUseCase listarProductosUseCase;
    private final ActualizarProductoUseCase actualizarProductoUseCase;
    private final EliminarProductoUseCase eliminarProductoUseCase;
    private final ContarProductosUseCase contarProductosUseCase;

    public ProductoModel crearProducto(String nombre, double precio) {
        return crearProductoUseCase.ejecutar(nombre, precio);
    }

    public ProductoModel obtenerProducto(Long id) {
        return buscarProductoPorIdUseCase.ejecutar(id);
    }

    public List<ProductoModel> listarProductos(int page, int size) {
        return listarProductosUseCase.ejecutar(page, size);
    }

    public ProductoModel actualizarProducto(Long id, String nombre, double precio) {
        return actualizarProductoUseCase.ejecutar(id, nombre, precio);
    }

    public Long eliminarProducto(Long id) {
        return eliminarProductoUseCase.ejecutar(id);
    }

    public Long contarTotal(){
        return contarProductosUseCase.ejecutar();
    }
}
