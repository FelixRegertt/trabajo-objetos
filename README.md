# Sistema de Gestión de Archivos y Carpetas en Entorno Colaborativo

## Introducción

El Sistema de Gestión de Archivos y Carpetas en un entorno colaborativo es una plataforma que permite a múltiples usuarios colaborar en la gestión de archivos y material académico en carpetas compartidas.

## Modelo de Datos

El sistema se basa en un modelo de datos que incluye los siguientes elementos:

- Usuarios: Representa a los usuarios del sistema.
- Cátedras: Define las diferentes cátedras de una carrera universitaria.
- Archivos: Los archivos son documentos o recursos que se almacenan en carpetas.
- Carpetas: Las carpetas son contenedores de archivos y pueden ser compartidas entre usuarios.
- Comentarios: Los usuarios pueden agregar comentarios a archivos y carpetas para facilitar la colaboración.

El sistema utiliza PostgreSQL como sistema de gestión de bases de datos para almacenar y gestionar estos elementos.

## Acceso a Datos

El acceso a los datos se realiza a través de JDBC (Java Database Connectivity), lo que permite la interacción con la base de datos PostgreSQL desde la aplicación.

## Filtros

Se implementan filtros para autorización y autenticación de usuarios, lo que garantiza la seguridad y el acceso controlado a los recursos. Se utiliza autenticación básica para verificar las credenciales de los usuarios en las solicitudes.

## Manejo de Credenciales

Las credenciales de los usuarios se verifican en cada solicitud, y se aplican diferentes criterios de autorización según los roles, como Admin, Admin o Autor, Autor Único y Credencial Simple.

## CORS

Se habilita el Cross-Origin Resource Sharing (CORS) para permitir solicitudes desde diferentes orígenes, lo que facilita la integración con otras aplicaciones y sistemas.

## Herramientas Utilizadas

El desarrollo del sistema se realiza con las siguientes herramientas y tecnologías:

- Tomcat 9.0.58: Servidor web para ejecutar la aplicación.
- PostgreSQL 12.09: Sistema de gestión de bases de datos.
- JDK 8: Plataforma de desarrollo Java.
- Bibliotecas (incluyendo Javax Servlet) definidas en pom.xml: Dependencias utilizadas en el proyecto.
- IntelliJ como IDE: Entorno de desarrollo integrado.
- Postman: Herramienta para el testeo de los endpoints.

## Despliegue en Heroku (finalizado de free tier)

El proyecto se encuentra desplegado en la plataforma Heroku, utilizando Heroku Postgres como base de datos en la nube. La URL base para acceder a la aplicación es [https://trabajo-objetos.herokuapp.com](https://trabajo-objetos.herokuapp.com), y se pueden encontrar endpoints disponibles en esa URL base para interactuar con los recursos del sistema.

- **USUARIO**: /usuarios
- **DIRECTORIO**: /directorio
- **COMENTARIO**: /comentario
- **CATEDRA**: /carpeta
- **CARPETA**: /catedra
- **ARCHIVO**: /archivo
- **FILTRO ARCHIVOS**: /filtro
- **TOP USUARIOS**: /usuarios/top
- **ARCHIVO FUENTE**: /archivo/fuente

El proyecto está diseñado para brindar una solución eficiente y segura para la gestión de archivos y carpetas en un entorno colaborativo, permitiendo a los usuarios académicos trabajar de manera efectiva.
