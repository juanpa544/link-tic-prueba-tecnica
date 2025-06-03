package co.juanpablancom.linkticprueba.inventario.infrastructure.web.controller.jsonapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JsonApiResponse<T> {
    private String type;
    private String id;
    private T attributes;
}

