package co.juanpablancom.linkticprueba.inventario.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ConsultarInventarioResponse {
    private String productoId;
    private String nombreProducto;
    private double precio;
    private long cantidadDisponible;
}
