package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import co.juanpablancom.linkticprueba.productos.application.dto.ActualizarProductoRequest;
import co.juanpablancom.linkticprueba.productos.application.dto.CrearProductoRequest;
import co.juanpablancom.linkticprueba.productos.application.dto.PaginaProductoResponse;
import co.juanpablancom.linkticprueba.productos.application.dto.ProductoResponse;
import co.juanpablancom.linkticprueba.productos.domain.model.ProductoModel;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductoDtoMapper {
    ProductoDtoMapper MAPPER = Mappers.getMapper(ProductoDtoMapper.class);

    ProductoModel toModel(CrearProductoRequest request);

    ProductoModel toModel(ActualizarProductoRequest request);

    ProductoResponse toResponse(ProductoModel model);

    List<ProductoResponse> toResponseList(List<ProductoModel> modelList);

    default PaginaProductoResponse toPagina(List<ProductoModel> productos, int page, int size, long totalElements) {
        return new PaginaProductoResponse(
                toResponseList(productos),
                page,
                size,
                totalElements
        );
    }
}