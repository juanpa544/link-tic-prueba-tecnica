package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.web.jsonapi.JsonApiPagedResponse;
import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.web.jsonapi.JsonApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoRestController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<JsonApiResponse<ProductoResponse>> crearProducto(@RequestBody @Valid CrearProductoRequest request) {
        ProductoModel productoCreado = productoService.crearProducto(request.getNombre(), request.getPrecio());
        ProductoResponse response = ProductoDtoMapper.MAPPER.toResponse(productoCreado);
        return ResponseEntity.ok(new JsonApiResponse<>("producto", String.valueOf(productoCreado.getId()), response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JsonApiResponse<ProductoResponse>> actualizarProducto(
            @PathVariable("id") long id,
            @Valid @RequestBody ActualizarProductoRequest request
    ) {
        ProductoModel productoActualizado = productoService.actualizarProducto(id, request.getNombre(), request.getPrecio());
        ProductoResponse response = ProductoDtoMapper.MAPPER.toResponse(productoActualizado);
        return ResponseEntity.ok(new JsonApiResponse<>("producto", String.valueOf(productoActualizado.getId()), response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonApiResponse<ProductoResponse>> obtenerProducto(@PathVariable("id") long id) {
        ProductoModel producto = productoService.obtenerProducto(id);
        ProductoResponse response = ProductoDtoMapper.MAPPER.toResponse(producto);
        return ResponseEntity.ok(new JsonApiResponse<>("producto", String.valueOf(id), response));
    }

    @GetMapping
    public ResponseEntity<JsonApiPagedResponse<ProductoResponse>> listarProductos(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
    List<ProductoModel> productos = productoService.listarProductos(page, size);
    long total = productoService.contarTotal();
    PaginaProductoResponse pagina = ProductoDtoMapper.MAPPER.toPagina(productos, page, size, total);    
    List<JsonApiResponse<ProductoResponse>> data = new ArrayList<>();
    for (ProductoModel p : productos) {
            // Creamos el objeto ProductoResponse con solo nombre y precio (sin id)
            ProductoResponse attributes = new ProductoResponse(p.getNombre(), p.getPrecio());    
            // Envolvemos en JsonApiResponse usando el id del modelo
            data.add(new JsonApiResponse<>("producto", String.valueOf(p.getId()), attributes));
    }    
    Map<String, Object> meta = Map.of(
            "total", pagina.getTotalElements(),
            "page", pagina.getPage(),
            "size", pagina.getSize()
    );    
    int totalPages = (int) Math.ceil((double) pagina.getTotalElements() / pagina.getSize());
    int currentPage = pagina.getPage();    
    Map<String, String> links = new HashMap<>();
    links.put("first", "/productos?page=0&size=" + size);
    links.put("last", "/productos?page=" + (totalPages - 1) + "&size=" + size);
    links.put("prev", currentPage > 0 ? "/productos?page=" + (currentPage - 1) + "&size=" + size : null);
    links.put("next", currentPage < totalPages - 1 ? "/productos?page=" + (currentPage + 1) + "&size=" + size : null);    
    return ResponseEntity.ok(new JsonApiPagedResponse<>(data, meta, links));
    }




} 

