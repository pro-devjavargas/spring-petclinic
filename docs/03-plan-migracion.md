# 03. Plan de Migración y Matriz de Equivalencia

## Secuencia de Transformación
1. **Fase 1:** Actualización de dependencias, configuración de Java 21 y validación de compilación limpia.
2. **Fase 2:** Implementación de DTOs y controladores REST desacoplados para Propietarios, Mascotas y Visitas.
3. **Fase 3:** Adaptación de pruebas unitarias y de controladores (`MockMvc`), manteniendo 78 pruebas en verde.
4. **Fase 4:** Documentación OpenAPI y empaquetado final del archivo `.jar`.

## Matriz de Equivalencia (Legacy vs Nueva API)
| Funcionalidad | Endpoint / Vista Legacy | Nuevo Endpoint REST |
| :--- | :--- | :--- |
| Listar Propietarios | `/owners.html` (Thymeleaf) | `GET /api/v1/owners` |
| Crear Propietario | Formulario Web | `POST /api/v1/owners` |
| Obtener Propietario | `/owners/{id}.html` | `GET /api/v1/owners/{ownerId}` |
| Registrar Visita | Formulario Web | `POST /api/v1/visits` |
| Listar Veterinarios | `/vets.html` | `GET /api/v1/vets` |
