package co.juanpablancom.linkticprueba.inventario.infrastructure.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.juanpablancom.linkticprueba.inventario.application.port.query.ProductoGateway;
import co.juanpablancom.linkticprueba.inventario.domain.exception.ProductoExternoNoEncontradoException;
import co.juanpablancom.linkticprueba.inventario.infrastructure.web.controller.jsonapi.JsonApiProductoResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoGatewayImpl implements ProductoGateway {

    private final RestTemplate restTemplate;

    @Value("${productos.service.url}")
    private String productosServiceUrl;

    @Override
    public JsonApiProductoResponse obtenerProductoPorId(long productoId) {
        try {
            String url = productosServiceUrl + "/productos/" + productoId;
            JsonNode root = restTemplate.getForObject(url, JsonNode.class);

            if (root != null && root.has("data")) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.treeToValue(root.get("data"), JsonApiProductoResponse.class);
            }

            throw new RuntimeException("Respuesta sin campo 'data'");
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductoExternoNoEncontradoException(productoId);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar el producto con ID " + productoId, e);
        }
    }


}