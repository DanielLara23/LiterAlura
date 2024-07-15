# Biblioteca Literaria

Este proyecto es una aplicación de gestión de libros y autores, que permite buscar libros, listar autores, y realizar consultas sobre obras literarias a través de una API externa.

## Tabla de Contenidos

- [Características](#características)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Licencia](#licencia)

## Características

- Buscar libros por título.
- Listar libros registrados.
- Listar autores registrados.
- Filtrar autores vivos en un año específico.
- Listar libros por idioma.
- Buscar autores por nombre.

## Requisitos

- Java 11 o superior.
- Maven.
- PostgreSQL (para la base de datos).
- Dependencias de Spring Boot y Hibernate.

## Instalación

1. Clona el repositorio:
   git clone https://github.com/tu_usuario/biblioteca-literaria.git

Configura la base de datos en application.properties:

properties

spring.datasource.url=jdbc:postgresql://localhost/"Nombre de tu base de datos"

spring.datasource.username="Tu usuario"

spring.datasource.password="Tu contraseña" 

spring.datasource.driver-class-name=org.postgresql.Driver

hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.hibernate.ddl-auto=update

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo LICENSE para más detalles.
