package co.juanpablancom.linkticprueba.productos.application.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActualizarProductoRequest {

    private String nombre;

    @Min(value = 1, message = "El precio debe ser mayor que 0")
    private double precio;
}
