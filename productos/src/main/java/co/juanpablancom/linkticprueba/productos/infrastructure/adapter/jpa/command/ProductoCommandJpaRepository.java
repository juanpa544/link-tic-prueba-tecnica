package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.jpa.command;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.entity.ProductoEntity;

@Repository
public interface ProductoCommandJpaRepository extends JpaRepository<ProductoEntity, String>{

}
