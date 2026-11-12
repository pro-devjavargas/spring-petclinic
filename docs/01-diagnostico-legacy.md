# 01. Diagnóstico del Proyecto Legacy (Spring PetClinic)

## 1. Tecnologías y Versiones Originales
- **Framework:** Spring Boot (versión base original).
- **Lenguaje:** Java (migrado a Java 21).
- **Persistencia:** Spring Data JPA con H2 en memoria por defecto (con soporte para MySQL y PostgreSQL mediante perfiles).
- **Vistas:** Arquitectura monolítica tradicional basada en Thymeleaf y Bootstrap.

## 2. Componentes y Funcionalidades Principales
- Módulo de Propietarios (`Owner` / `Pet`).
- Módulo de Visitas médicas (`Visit`).
- Módulo de Veterinarios (`Vet` / `Specialty`).

## 3. Deuda Técnica y Oportunidades de Modernización
- Acoplamiento fuerte entre las vistas HTML (Thymeleaf) y los controladores web tradicionales.
- Ausencia de una capa de DTOs formal separada de las entidades de persistencia.
- Falta de contratos de API REST estandarizados y documentación interactiva para clientes externos.
