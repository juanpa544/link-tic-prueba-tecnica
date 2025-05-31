package co.juanpablancom.linkticprueba.productos.application.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginaProductoResponse {
    private List<ProductoResponse> content;
    private int page;
    private int size;
    private long totalElements;
}
