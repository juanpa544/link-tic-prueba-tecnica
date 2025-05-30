package co.juanpablancom.linkticprueba.inventario.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "inventario")
public class InventarioEntity {

    @Id
    @Column(name = "producto_id")
    private String productoId;

    @Column(name = "cantidad")
    private long cantidad;
}
