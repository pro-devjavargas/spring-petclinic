# 02. Arquitectura de la Solución Migrada

## 1. Enfoque Arquitectónico
Se ha optado por un **Monolito Modular** bien delimitado, separando responsabilidades por dominios de negocio sin la complejidad innecesaria de microservicios distribuidos:
- `owner`: Gestión unificada de propietarios y mascotas.
- `visits`: Registro y consulta de visitas médicas asociadas.
- `vet`: Consulta de veterinarios y especialidades.

## 2. Decisiones de Diseño
- **DTOs (Data Transfer Objects):** Separación estricta entre las entidades JPA y los contratos de entrada/salida (`Request`/`Response DTOs`).
- **Documentación API:** Integración de **Springdoc OpenAPI (Swagger UI)** para pruebas interactivas en `/swagger-ui.html`.
- **Manejo de Errores:** Centralización de excepciones HTTP con códigos de estado consistentes.
