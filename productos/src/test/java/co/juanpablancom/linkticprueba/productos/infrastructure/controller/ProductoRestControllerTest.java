package co.juanpablancom.linkticprueba.productos.infrastructure.controller;

import co.juanpablancom.linkticprueba.productos.application.dto.CrearProductoRequest;
import jakarta.transaction.Transactional;
import co.juanpablancom.linkticprueba.productos.application.dto.ActualizarProductoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CrearProductoRequest crearProductoRequestValido;

    @BeforeEach
    void setUp() {
        crearProductoRequestValido = new CrearProductoRequest("Producto Test", 100.0);
    }

    @Test
    void crearProductoDebeFuncionar() throws Exception {
        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crearProductoRequestValido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto Test"))
                .andExpect(jsonPath("$.precio").value(100.0));
    }

    @Test
    void crearProductoConPrecioInvalidoDebeFallar() throws Exception {
        CrearProductoRequest invalido = new CrearProductoRequest("Producto Inválido", -10.0);

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalido)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void obtenerProductoPorIdDebeFuncionar() throws Exception {
        String response = mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crearProductoRequestValido)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = JsonPath.parse(response).read("$.id", Long.class);

        mockMvc.perform(get("/productos/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto Test"));
    }

    @Test
    void actualizarProductoDebeFuncionar() throws Exception {
        String response = mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crearProductoRequestValido)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = JsonPath.parse(response).read("$.id", Long.class);

        ActualizarProductoRequest update = new ActualizarProductoRequest("Actualizado", 200.0);

        mockMvc.perform(put("/productos/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Actualizado"))
                .andExpect(jsonPath("$.precio").value(200.0));
    }

    @Test
    void eliminarProductoDebeFuncionar() throws Exception {
        String response = mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crearProductoRequestValido)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = JsonPath.parse(response).read("$.id", Long.class);

        mockMvc.perform(delete("/productos/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/productos/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void listarProductosDebeFuncionar() throws Exception {
        mockMvc.perform(get("/productos")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}

