# TallerMecanico-2DAM
Práctica final de 2DAM que implica los módulos de Acceso a Datos, Programación de Servicios y Procesos, Diseño de Interfaces y Desarrollo de Aplicaciones Moviles Android

![Talleres dam](https://user-images.githubusercontent.com/91011630/152509949-111d610f-0b0a-47c2-b545-d46297fe845b.png)

## Backend

Para esta parte utilizaremos varias tecnologías:
- Spring como Framework
- MariaDB como Base de Datos
- API REST

# Reparto de tareas en el proyecto

Para la realicación de este trabajao decidimos especializar a cada miembro en una de las partes en las que podríamos descomponerlo: interfaces, móviles, seguridad, API Rest y JPA. Por ello, una de las primeras acciones que tomamos fue elegir algunos vídeos o cursos de OpenWebinars interesantes para que cada uno consultase por su cuenta, y que de este modo tuviese los conocimientos para ayudar al resto cuando estuviesen trabajando en la parte de la que ellos eran "expertos".

Remarcar que hemos intentado a lo largo de toda la realización intervenir en todas las partes. Aunque sea en mayor o menor medida, todos hemos desarrollado algo en cada parte y hemos prestado ayuda a los compañeros que quizá iban más ahogados de trabajo.

Dicho esto, haremos un pequeño repaso de las funciones donde ha destacado más cada miembro, y aquellas cosas que se hicieron en consenso y se debatieron como grupo.

### General

- Establecimiento y reflexión sobre los requisitos de la aplicación
- División del trabajo y establecimiento de fechas límite
- Creación del UML inicial (que iría cambiando durante el desarrollo)
- Definición de relaciones entre entidades
- Elección del logotipo y colores representativos en el front (grises, blancos y rojos)
- Creación del Trello como herramienta de comunicación en la distribución del trabajo
- Creación de los repositorios comunes
- Elección de los endpoints
- Diseño inicial de la distribución de pantallas en Android y Escritorio

---

### Adrian

Responsable del área de JPA y del diseño general del servidor. En este apartado realiza tareas como:

   - Actualización de entidades y relaciones en el diagrama UML a lo largo del proyecto
   - Creación de dao y establecimiento de las relaciones con JPA
   - Creación de repositorios generales
   - Testeo de los repositorios
   - Establecimiento del entorno de pruebas con H2

También interviene en las clases comunes a JPA y API Rest, como son los controladores,
servicios y mapper. En la sección de móvil, destaca su ayuda en el establecimiento de la comunicación entre la API y la aplicación cliente.

---

### Javier

Jefe de la parte relacionada con API Rest y encargado de los test:

   - Implementación de los controladores ( Configuración y conexión a la API)
   - Creación y adaptación de mappers y dto
   - Consultas personalizadas y pageables
   - Testeo de Controladores con Mockito

Fuera de su sección, interviene en el diseño de la aplicación de Escritorio y hace los mockup de este cliente. Además, facilita cursos y vídeos de ayuda al resto de compañeros durante todo el proyecto.

---

### Mario

Representante del apartado de seguridad, lo que implica:

   - Adaptar el modelo de Usuario a las necesidades de seguridad (implementar UserDetails, password, username...)
   - Nuevos dto de transferencia de datos
   - Creación y configuración de clases de seguridad con JWT
   - Creación de clases de errores (Consulta, seguridad y ficheros)

También en la parte de servidor, aunque fuera de la temática de seguridad, coge peso en la creación de logs con log4j,
documentación con Swagger y crear las clases necesarias para la subida de archivos con peticiones multiparte.

Fuera de este apartado, hace una estructura inicial de los layout de Android (posteriormente modificados por Andrea) y ayuda en la consulta de API Rest desde el cliente de Escritorio.

---

### Andrea

Andrea ha sido la experta en Aplicaciones Móviles, y ha sido la que más peso ha tenido
en este apartado:

   - Diseño definitivo de la aplicación
   - Realización de Mockup
   - Crear los layouts definitivos (Mejora de la estética)
   - Funcionalidad en menús, RecyclerViews, vistas detalle...

Fuera de su sección, destaca su labor en la parte de servidor, donde ha intervenido especialmente en la lógica de creación de las citas, participando en DTOs, Mappers
y Servicios en las clases implicadas, especialmente, Cita, Vehículo y Usuario.

---

### Daniel

El rol de Daniel ha sido el de encargado de la Interfaz, donde ha realizado tareas como:

   - Diseño definitivo de la aplicación
   - Creación de los diferentes menús y vistas
   - Funcionalidad general de la interfaz
   - Peticiones a la API Rest

Por otro lado, además de su ayuda en las labores generales, se ha encargado junto a Adrian de las pruebas en Postman, ha intervenido en la adaptación tanto de los DTO como de los controladores en el servidor para facilitar la transmisión de datos a la interfaz, al igual que modificar los datos de prueba en el fichero sql.
