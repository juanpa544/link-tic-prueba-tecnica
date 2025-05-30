package co.juanpablancom.linkticprueba.inventario.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import co.juanpablancom.linkticprueba.inventario.application.dto.ProductoResponse;
import co.juanpablancom.linkticprueba.inventario.application.port.query.ProductoGateway;
import co.juanpablancom.linkticprueba.inventario.domain.exception.ProductoExternoNoEncontradoException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoClientImpl implements ProductoGateway {

    private final RestTemplate restTemplate;

    @Value("${productos.service.url}")
    private String productosServiceUrl;

    @Override
    public ProductoResponse obtenerProductoPorId(String productoId) {
        try {
            String url = productosServiceUrl + "/productos/" + productoId;
            return restTemplate.getForObject(url, ProductoResponse.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductoExternoNoEncontradoException(productoId);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar el producto con ID " + productoId, e);
        }
    }
}