package co.juanpablancom.linkticprueba.inventario.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActualizarInventarioRequest {

    @NotBlank(message = "El productoId es obligatorio")
    private String productoId;

    @Min(value = 0, message = "La cantidad debe ser igual o mayor a 0")
    private long cantidad;
}