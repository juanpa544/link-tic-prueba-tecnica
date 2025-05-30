package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.jpa.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;
import co.juanpablancom.linkticprueba.productos.infrastructure.adapter.entity.ProductoEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductoMapper {
    ProductoMapper MAPPER = Mappers.getMapper(ProductoMapper.class);

	ProductoModel toModel(ProductoEntity productoEntity);

	@InheritInverseConfiguration
	ProductoEntity toEntity(ProductoModel Producto);

	List<ProductoModel> toModelList(List<ProductoEntity> productoEntityList);

	List<ProductoEntity> toEntityList(List<ProductoModel> productoList);
}
