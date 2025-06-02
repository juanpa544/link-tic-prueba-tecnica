package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.jpa.query;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.entity.ProductoEntity;

@Repository
public interface ProductoQueryJpaRepository extends JpaRepository<ProductoEntity, Long>{

    Optional<ProductoEntity> findById(long id);

    Page<ProductoEntity> findAll(Pageable pageable);

}
