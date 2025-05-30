package co.juanpablancom.linkticprueba.productos.application.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PaginaProductoResponse {
    private final List<ProductoResponse> content;
    private final int page;
    private final int size;
    private final long totalElements;
}
