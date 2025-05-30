package co.juanpablancom.linkticprueba.inventario.infrastructure.jpa.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import co.juanpablancom.linkticprueba.inventario.domain.model.InventarioModel;
import co.juanpablancom.linkticprueba.inventario.infrastructure.entity.InventarioEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InventarioMapper {
    InventarioMapper MAPPER = Mappers.getMapper(InventarioMapper.class);

	InventarioModel toModel(InventarioEntity inventarioEntity);

	@InheritInverseConfiguration
	InventarioEntity toEntity(InventarioModel Inventario);

	List<InventarioModel> toModelList(List<InventarioEntity> inventarioEntityList);

	List<InventarioEntity> toEntityList(List<InventarioModel> inventarioList);
}
