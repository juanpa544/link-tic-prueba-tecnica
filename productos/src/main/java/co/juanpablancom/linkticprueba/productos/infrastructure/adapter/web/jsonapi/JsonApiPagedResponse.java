package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.web.jsonapi;

import java.util.List;
import java.util.Map;

public class JsonApiPagedResponse<T> {
    private List<JsonApiResponse<T>> data;
    private Map<String, Object> meta;
    private Map<String, String> links;

    public JsonApiPagedResponse(List<JsonApiResponse<T>> data,
                                 Map<String, Object> meta,
                                 Map<String, String> links) {
        this.data = data;
        this.meta = meta;
        this.links = links;
    }

    public List<JsonApiResponse<T>> getData() { return data; }
    public Map<String, Object> getMeta() { return meta; }
    public Map<String, String> getLinks() { return links; }
}

