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
public class CompraInventarioRequest {

    @NotBlank(message = "El ID del producto no puede estar vacío.")
    private String productoId;

    @Min(value = 1, message = "La cantidad comprada debe ser al menos 1.")
    private long cantidadComprada;
}
