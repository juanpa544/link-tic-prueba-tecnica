package co.juanpablancom.linkticprueba.inventario.infrastructure.web.controller.jsonapi;

import co.juanpablancom.linkticprueba.inventario.application.dto.ProductoAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonApiProductoResponse {
    private String type;
    private String id;
    private ProductoAttributes attributes;
}

