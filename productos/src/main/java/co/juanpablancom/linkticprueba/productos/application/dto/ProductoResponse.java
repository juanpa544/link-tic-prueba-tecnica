package co.juanpablancom.linkticprueba.productos.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ProductoResponse {
    private final String id;
    private final String nombre;
    private final double precio;
}
