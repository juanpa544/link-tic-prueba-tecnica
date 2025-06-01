package co.juanpablancom.linkticprueba.productos.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.domain.port.query.ProductoQuery;
import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.entity.ProductoEntity;
import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.jpa.mapper.ProductoMapper;
import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.jpa.query.ProductoQueryJpaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class ProductoQueryImpl implements ProductoQuery{

    private ProductoQueryJpaRepository productoQueryJpaRepository;
    
    @Override
    public Optional<ProductoModel> buscarPorId(Long id) {
        return productoQueryJpaRepository.findById(id)
                .map(ProductoMapper.MAPPER::toModel);
    }

    @Override
    public List<ProductoModel> listarProductos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductoEntity> pagina = productoQueryJpaRepository.findAll(pageable);

        return ProductoMapper.MAPPER.toModelList(pagina.getContent());
    }

    @Override
    public long contarTotal() {
        return productoQueryJpaRepository.count();
    }

}
