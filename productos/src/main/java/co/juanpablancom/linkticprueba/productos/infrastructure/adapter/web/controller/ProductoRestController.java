package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.juanpablancom.linkticprueba.productos.application.dto.ActualizarProductoRequest;
import co.juanpablancom.linkticprueba.productos.application.dto.CrearProductoRequest;
import co.juanpablancom.linkticprueba.productos.application.dto.PaginaProductoResponse;
import co.juanpablancom.linkticprueba.productos.application.dto.ProductoResponse;
import co.juanpablancom.linkticprueba.productos.application.service.ProductoService;
import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.jpa.mapper.ProductoDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoRestController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponse> crearProducto(@Valid @RequestBody CrearProductoRequest request) {
        ProductoModel productoCreado = productoService.crearProducto(request.getNombre(), request.getPrecio());
        return ResponseEntity.ok(ProductoDtoMapper.MAPPER.toResponse(productoCreado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(
            @PathVariable String id,
            @Valid @RequestBody ActualizarProductoRequest request
    ) {
        ProductoModel productoActualizado = productoService.actualizarProducto(id, request.getNombre(), request.getPrecio());
        return ResponseEntity.ok(ProductoDtoMapper.MAPPER.toResponse(productoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerProducto(@PathVariable String id) {
        ProductoModel producto = productoService.obtenerProducto(id);
        return ResponseEntity.ok(ProductoDtoMapper.MAPPER.toResponse(producto));
    }

    @GetMapping
    public ResponseEntity<PaginaProductoResponse> listarProductos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ProductoModel> productos = productoService.listarProductos(page, size);
        long total = productoService.contarTotal();
        PaginaProductoResponse respuesta = ProductoDtoMapper.MAPPER.toPagina(productos, page, size, total);
        return ResponseEntity.ok(respuesta);
    }
}
