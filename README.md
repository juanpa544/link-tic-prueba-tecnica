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

## 🔐 Autenticación entre Microservicios

- Cada microservicio valida las solicitudes entrantes mediante un header `X-API-KEY`.
- Las claves se configuran como propiedades (`application.yml`).
- Se implementa un filtro interceptor para validar API Keys.

---

## ⚙️ JSON:API

Todos los endpoints devuelven respuestas alineadas con el estándar [JSON:API](https://jsonapi.org/), estructuradas con las claves:
- `data`
- `type`
- `id`
- `attributes`

---

## 🧪 Pruebas

### ✅ Pruebas Unitarias
- **Cobertura total > 80%**
- Casos cubiertos:
  - Creación de productos.
  - Validaciones de dominio (`precio`, `nombre`, `productoId`, `cantidad`).
  - Comunicación fallida entre microservicios.
  - Reducción de inventario.
  - Excepciones por producto duplicado o no encontrado.

### ✅ Pruebas de Integración
- Se valida:
  - Comunicación exitosa entre los microservicios.
  - Persistencia en la base de datos embebida.
  - API JSON consumida correctamente.

---

## 🐳 Docker y Despliegue

### 🔧 Requisitos
- Docker Desktop

### ▶️ Para ejecutar el sistema:
```bash
docker-compose up --build
