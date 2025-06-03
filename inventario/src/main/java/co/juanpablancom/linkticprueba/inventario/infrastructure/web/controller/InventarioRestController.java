package co.juanpablancom.linkticprueba.inventario.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.juanpablancom.linkticprueba.inventario.application.dto.ActualizarInventarioRequest;
import co.juanpablancom.linkticprueba.inventario.application.dto.CompraInventarioRequest;
import co.juanpablancom.linkticprueba.inventario.application.dto.ConsultarInventarioResponse;
import co.juanpablancom.linkticprueba.inventario.application.service.InventarioService;
import co.juanpablancom.linkticprueba.inventario.infrastructure.web.controller.jsonapi.JsonApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventarios")
@RequiredArgsConstructor
public class InventarioRestController {

    private final InventarioService inventarioService;

    @GetMapping("/{productoId}")
    public ResponseEntity<JsonApiResponse<ConsultarInventarioResponse>> consultarInventario(@PathVariable("productoId") long productoId) {
        ConsultarInventarioResponse response = inventarioService.consultarInventario(productoId);
        JsonApiResponse<ConsultarInventarioResponse> jsonApi = new JsonApiResponse<>(
                "inventario",
                String.valueOf(productoId),
                response
        );
        return ResponseEntity.ok(jsonApi);
    }

    @PutMapping
    public ResponseEntity<Void> actualizarInventario(@RequestBody @Valid ActualizarInventarioRequest request) {
        inventarioService.actualizarInventario(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/comprar")
    public ResponseEntity<Void> aplicarCompra(@RequestBody @Valid CompraInventarioRequest request) {
        inventarioService.aplicarCompra(request.getProductoId(), request.getCantidadComprada());
        return ResponseEntity.noContent().build();
    }
}
