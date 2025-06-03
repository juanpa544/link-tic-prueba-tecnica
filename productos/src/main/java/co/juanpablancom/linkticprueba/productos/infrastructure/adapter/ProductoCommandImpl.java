package co.juanpablancom.linkticprueba.productos.infrastructure.adapter;

import org.springframework.stereotype.Repository;

import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.command.ProductoCommand;
import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.entity.ProductoEntity;
import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.jpa.command.ProductoCommandJpaRepository;
import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.jpa.mapper.ProductoMapper;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ProductoCommandImpl implements ProductoCommand {

    private final ProductoCommandJpaRepository productoCommandJpaRepository;

    @Override
    public ProductoModel crear(ProductoModel productoModel) {
        ProductoEntity productoEntity = ProductoMapper.MAPPER.toEntity(productoModel);
        ProductoModel productoCreado = ProductoMapper.MAPPER.toModel(productoCommandJpaRepository.save(productoEntity));
        return productoCreado;
    }

    @Override
    public ProductoModel actualizar(ProductoModel productoModel) {
        productoCommandJpaRepository.save(ProductoMapper.MAPPER.toEntity(productoModel));
        return productoModel;
    }

    @Override
    public Long eliminar(long id) {
        productoCommandJpaRepository.deleteById(id);
        return id;
    }
}