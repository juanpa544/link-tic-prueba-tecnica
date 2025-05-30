package co.juanpablancom.linkticprueba.productos.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class CrearProductoRequest {

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    @Min(value = 1, message = "El precio debe ser mayor que 0")
    private double precio;
}
