package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.web.jsonapi;

import java.util.List;

public class JsonApiListResponse<T> {
    private List<JsonApiResponse.JsonApiData<T>> data;

    public JsonApiListResponse(List<JsonApiResponse.JsonApiData<T>> data) {
        this.data = data;
    }

    public List<JsonApiResponse.JsonApiData<T>> getData() {
        return data;
    }
}

