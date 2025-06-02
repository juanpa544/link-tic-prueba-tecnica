package co.juanpablancom.linkticprueba.inventario.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import co.juanpablancom.linkticprueba.inventario.domain.port.query.InventarioQuery;
import co.juanpablancom.linkticprueba.inventario.infrastructure.jpa.mapper.InventarioMapper;
import co.juanpablancom.linkticprueba.inventario.infrastructure.jpa.query.InventarioQueryJpaRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InventarioQueryImpl implements InventarioQuery {

    private final InventarioQueryJpaRepository inventarioQueryJpaRepository;

    @Override
    public Optional<InventarioModel> obtenerPorProductoId(long productoId) {
        return inventarioQueryJpaRepository.findByProductoId(productoId)
                .map(InventarioMapper.MAPPER::toModel);
    }
}
