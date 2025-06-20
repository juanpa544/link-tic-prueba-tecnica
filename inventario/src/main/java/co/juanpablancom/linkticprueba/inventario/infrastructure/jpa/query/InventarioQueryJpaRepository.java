package co.juanpablancom.linkticprueba.inventario.infrastructure.jpa.query;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.juanpablancom.linkticprueba.inventario.infrastructure.entity.InventarioEntity;

public interface InventarioQueryJpaRepository extends JpaRepository<InventarioEntity, Long>{

    Optional<InventarioEntity> findByProductoId(long productoId);
    
}
