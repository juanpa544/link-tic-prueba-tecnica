package co.juanpablancom.linkticprueba.inventario.application.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActualizarInventarioRequest {

    @Min(value = 1, message = "El id del producto debe ser igual o mayor a 1")
    private long productoId;

    @Min(value = 0, message = "La cantidad debe ser igual o mayor a 0")
    private long cantidad;
}