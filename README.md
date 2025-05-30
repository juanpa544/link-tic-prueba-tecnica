# LinkTic - Prueba Técnica Backend

## 🧠 Descripción General

Esta solución consiste en dos microservicios independientes que gestionan productos e inventario, comunicándose entre sí vía HTTP utilizando el estándar **JSON:API**. Los microservicios están desacoplados, orquestados mediante **Docker Compose**, y cada uno cuenta con su propia base de datos embebida (H2) para facilitar el despliegue y pruebas. Se han aplicado buenas prácticas de arquitectura, pruebas, documentación y seguridad.

---

## 📦 Estructura del Proyecto


---

## 🧩 Microservicios

### 1. 🛒 **Microservicio de Productos**
- **Responsabilidad:** Gestión CRUD de productos.
- **Campos:** `id`, `nombre`, `precio`.
- **Endpoints implementados:**
  - `POST /productos`: Crear producto.
  - `GET /productos/{id}`: Obtener producto por ID.
  - `PUT /productos/{id}`: Actualizar producto por ID.
  - `DELETE /productos/{id}`: Eliminar producto.
  - `GET /productos`: Listar productos con paginación.
- **Base de datos:** Embebida H2.

### 2. 📦 **Microservicio de Inventario**
- **Responsabilidad:** Consulta y actualización de inventario.
- **Campos:** `producto_id`, `cantidad`.
- **Endpoints implementados:**
  - `GET /inventarios/{productoId}`: Consultar inventario (llama al microservicio de productos).
  - `PUT /inventarios`: Actualizar inventario.
  - `POST /inventarios/comprar`: Aplicar compra (verifica existencia del producto y stock disponible).
- **Eventos:** Emisión de logs en consola al cambiar el inventario.
- **Base de datos:** Embebida H2.

---

## ⚙️ JSON:API

Todos los endpoints devuelven respuestas de tipo JSON.API

---

## 🧪 Pruebas

### ✅ Pruebas Unitarias
- Casos cubiertos:
  - Creación de productos.
  - Validaciones de dominio (`precio`, `nombre`, `productoId`, `cantidad`).
  - Comunicación fallida entre microservicios.
  - Reducción de inventario.
  - Excepciones por producto duplicado o no encontrado.

### ✅ Pruebas de Integración
- Se valida:
  - Persistencia en la base de datos embebida.
  - API JSON consumida correctamente.

---

## 🐳 Docker y Despliegue

### 🔧 Requisitos
- Docker Desktop

### ▶️ Para ejecutar el sistema:
```bash
docker-compose up --build
```
Esto levantará ambos microservicios expuestos en:

Productos: http://localhost:8080

Inventario: http://localhost:8082

Swagger disponible en:

http://localhost:8082/swagger-ui/index.html

http://localhost:8080/swagger-ui/index.html


###📐 Arquitectura

Comunicación HTTP entre microservicios.

Validaciones y lógica encapsuladas en casos de uso (UseCase).

Separación clara por capas (adaptadores, aplicación, dominio).

Patrón de arquitectura Hexagonal (Ports & Adapters).

## 🧑‍💻 Guía para Nuevos Desarrolladores
1. Clona el proyecto.

2. Asegúrate de tener Docker instalado.

3. Ejecuta docker-compose up --build.

4. Consulta la documentación Swagger.

5. Ejecuta las pruebas con ./gradlew test.

Agrega nuevas funcionalidades siguiendo el patrón de casos de uso y arquitectura hexagonal.

## 📌 Consideraciones
Se utilizaron bases de datos embebidas por simplicidad, velocidad de desarrollo y portabilidad.

## 📚 Tecnologías Usadas
Java 21

Spring Boot 3

JUnit 5, Mockito

Swagger/OpenAPI

Docker

H2 Embedded Database

REST + JSON:API

## ✅ Pendientes / Mejoras Futuras
Implementar base de datos externa en producción.

Agregar monitoreo con Prometheus + Grafana.

Registrar eventos en Kafka u otro broker.

Agregar métricas e integraciones con Zipkin o Jaeger.

## 📬 Contacto
Para cualquier duda técnica sobre esta implementación, puedes comunicarte con:

Juan Pablo Blanco Márquez
Desarrollador Backend
📧 jblancomarquez54@gmail.com
📱 +57 350 5185330
