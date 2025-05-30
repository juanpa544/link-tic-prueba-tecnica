package co.juanpablancom.linkticprueba.productos.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoResponse {
    private String id;
    private String nombre;
    private double precio;
}
