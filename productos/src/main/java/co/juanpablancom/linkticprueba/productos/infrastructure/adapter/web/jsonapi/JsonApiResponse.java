package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.web.jsonapi;

public class JsonApiResponse<T> {
    private JsonApiData<T> data;

    public JsonApiResponse(String type, String id, T attributes) {
        this.data = new JsonApiData<>(type, id, attributes);
    }

    public JsonApiData<T> getData() {
        return data;
    }

    public static class JsonApiData<T> {
        private String type;
        private String id;
        private T attributes;

        public JsonApiData(String type, String id, T attributes) {
            this.type = type;
            this.id = id;
            this.attributes = attributes;
        }

        public String getType() { return type; }
        public String getId() { return id; }
        public T getAttributes() { return attributes; }
    }
}

