package co.juanpablancom.linkticprueba.inventario.infrastructure.jpa.command;

import org.springframework.data.jpa.repository.JpaRepository;

import co.juanpablancom.linkticprueba.inventario.infrastructure.entity.InventarioEntity;

public interface InventarioCommandJpaRepository extends JpaRepository<InventarioEntity, String>{

}
