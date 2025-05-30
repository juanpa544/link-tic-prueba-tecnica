package co.juanpablancom.linkticprueba.inventario.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ProductoResponse {
    private String id;
    private String nombre;
    private double precio;
}
