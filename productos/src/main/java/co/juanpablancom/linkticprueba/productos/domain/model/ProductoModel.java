package co.juanpablancom.linkticprueba.productos.domain.model;

import co.juanpablancom.linkticprueba.productos.domain.exception.PrecioInvalidoException;
import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoSinNombreException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoModel {
    private String id;
    private String nombre;
    private double precio;

    public ProductoModel(String id, String nombre, double precio) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ProductoSinNombreException();
        }
        if (precio <= 0) {
            throw new PrecioInvalidoException(precio);
        }

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
}
