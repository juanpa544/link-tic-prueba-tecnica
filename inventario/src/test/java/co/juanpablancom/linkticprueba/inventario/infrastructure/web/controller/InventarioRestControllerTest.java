package co.juanpablancom.linkticprueba.inventario.infrastructure.web.controller;

import co.juanpablancom.linkticprueba.inventario.InventarioApplication;
import co.juanpablancom.linkticprueba.inventario.application.dto.ActualizarInventarioRequest;
import co.juanpablancom.linkticprueba.inventario.application.dto.CompraInventarioRequest;
import co.juanpablancom.linkticprueba.inventario.application.dto.ConsultarInventarioResponse;
import co.juanpablancom.linkticprueba.inventario.infrastructure.entity.InventarioEntity;
import co.juanpablancom.linkticprueba.inventario.infrastructure.jpa.command.InventarioCommandJpaRepository;
import co.juanpablancom.linkticprueba.inventario.infrastructure.jpa.query.InventarioQueryJpaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = InventarioApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InventarioRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InventarioCommandJpaRepository inventarioCommandJpaRepository;

    @Autowired
    private InventarioQueryJpaRepository inventarioQueryJpaRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/inventarios";
        inventarioCommandJpaRepository.deleteAll();
        inventarioCommandJpaRepository.save(new InventarioEntity("PROD-123", 20));
    }

    @Test
    void debeConsultarInventarioPorProductoId() {
        // Act
        ResponseEntity<ConsultarInventarioResponse> response = restTemplate.getForEntity(
                baseUrl + "/PROD-123", ConsultarInventarioResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("PROD-123", response.getBody().getProductoId());
        assertEquals(20, response.getBody().getCantidadDisponible());
    }

    @Test
    void debeActualizarInventario() {
        ActualizarInventarioRequest request = new ActualizarInventarioRequest("PROD-123", 35);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ActualizarInventarioRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                baseUrl, HttpMethod.PUT, entity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verifica en la base de datos embebida
        var inventario = inventarioQueryJpaRepository.findByProductoId("PROD-123").get();
        assertEquals(35, inventario.getCantidad());
    }

    @Test
    void debeAplicarCompraYReducirInventario() {
        CompraInventarioRequest request = new CompraInventarioRequest("PROD-123", 5);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CompraInventarioRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(
                baseUrl + "/comprar", entity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verifica en la base de datos embebida
        var inventario = inventarioQueryJpaRepository.findByProductoId("PROD-123").get();
        assertEquals(15, inventario.getCantidad());
    }
}

