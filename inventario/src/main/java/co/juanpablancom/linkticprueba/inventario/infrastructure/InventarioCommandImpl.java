package co.juanpablancom.linkticprueba.inventario.infrastructure;

import org.springframework.stereotype.Repository;

import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import co.juanpablancom.linkticprueba.inventario.domain.port.command.InventarioCommand;
import co.juanpablancom.linkticprueba.inventario.infrastructure.jpa.command.InventarioCommandJpaRepository;
import co.juanpablancom.linkticprueba.inventario.infrastructure.jpa.mapper.InventarioMapper;
import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class InventarioCommandImpl implements InventarioCommand {

    private final InventarioCommandJpaRepository inventarioCommandJpaRepository;

    @Override
    public InventarioModel actualizarCantidad(String productoId, long nuevaCantidad) {
        InventarioModel inventario = new InventarioModel(productoId, nuevaCantidad);
        inventarioCommandJpaRepository.save(InventarioMapper.MAPPER.toEntity(inventario));
        return inventario;
    }
}
