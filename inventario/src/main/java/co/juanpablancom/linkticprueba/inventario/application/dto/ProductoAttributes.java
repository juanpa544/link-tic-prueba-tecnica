package co.juanpablancom.linkticprueba.inventario.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoAttributes {
    private String nombre;
    private double precio;
}
