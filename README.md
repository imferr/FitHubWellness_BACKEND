# FitHub Wellness - Backend

<div align="center">
  <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/postgresql_logo_icon_170836.png" alt="FitHub Wellnes" width="300"/>
  <img src="https://bgasparotto.com/wp-content/uploads/2017/12/spring-logo.png" alt="FitHub Wellnes" width="300"/>
</div>

## Descripción

Este proyecto corresponde al backend de la aplicación FitHub Wellness, el cual se encarga de la conexión con la base de datos y la lógica de negocio. Este proyecto se encuentra desarrollado en Java 17 y Spring Boot 2.5.5. Para la base de datos se utiliza PostgreSQL 13.4. Y Maven 3.6.3 para la gestión de dependencias.

## Requerimientos

Para hacer correr este proyecto se necesita tener instalado:

- Maven 3.6.3
- Java 17
- Docker 20.10.8
- PostgreSQL 13.4

## Crear y ejecutar contenedor de Docker con PostgreSQL

1. Descargar la imagen de PostgreSQL:

    ```bash
    docker pull postgres
    ```

2. Luego se debe crear el contenedor:

    ```bash
    docker run --name fithub -e POSTGRES_PASSWORD=abc123 -d -p 5434:5432 postgres
    ```

3. Para verificar que el contenedor se encuentra corriendo se debe ejecutar el siguiente comando:

    ```bash
    docker ps
    ```

4. Para conectarse a la base de datos se debe ejecutar el siguiente comando:

    ```bash
    docker exec -it fithub psql -U postgres
    ```

5. Para crear la base de datos se debe ejecutar el siguiente comando:

    ```bash
    CREATE DATABASE bddfithub;
    ```

6. Para conectarse a la base de datos creada se debe ejecutar el siguiente comando:

    ```bash
    \c bddfithub
    ```

7. Posteriormente se debe copiar todo el contenido del archivo `../fithubwelness/bdd/bdd.sql` y pegarlo en la terminal.

8. De esta forma se creará la base de datos con sus respectivas tablas y datos.

## Ejecución

Para compilar el proyecto se debe correr el siguiente comando:

```bash
mvn clean package
```

Para ejecutar el proyecto se debe correr el siguiente comando:

```bash
mvn spring-boot:run
```