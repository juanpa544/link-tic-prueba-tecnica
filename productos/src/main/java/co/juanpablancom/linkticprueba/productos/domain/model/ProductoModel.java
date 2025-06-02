package co.juanpablancom.linkticprueba.productos.domain.model;

import co.juanpablancom.linkticprueba.productos.domain.exception.PrecioInvalidoException;
import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoSinNombreException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoModel {
    private long id;
    private String nombre;
    private double precio;

    public ProductoModel(String nombre, double precio) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ProductoSinNombreException();
        }
        if (precio <= 0) {
            throw new PrecioInvalidoException(precio);
        }

        this.nombre = nombre;
        this.precio = precio;
    }
}
