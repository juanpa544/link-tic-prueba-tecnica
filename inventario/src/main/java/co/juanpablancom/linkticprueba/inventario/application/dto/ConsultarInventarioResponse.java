package co.juanpablancom.linkticprueba.inventario.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultarInventarioResponse {
    private String productoId;
    private String nombreProducto;
    private double precio;
    private long cantidadDisponible;
}
