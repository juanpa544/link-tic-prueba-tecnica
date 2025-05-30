package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class ProductoEntity {

    @Id
	@Column(name = "id")
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private double precio;
}