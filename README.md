# Proyecto 1 DEIN

Este es un proyecto desarrollado en JavaFX para gestionar información sobre deportes, deportistas, equipos, eventos, olimpiadas y participaciones en olimpiadas. El proyecto incluye interfaces gráficas para agregar, modificar y visualizar información relacionada con estos elementos. Además, utiliza una base de datos para almacenar y gestionar los datos de los participantes y las olimpiadas.

## Estructura del Proyecto

### 1. **Carpetas Principales:**
- **`application/`**: Contiene la clase principal del proyecto y los controladores para las diferentes vistas.
- **`bbdd/`**: Contiene la clase `ConexionBD.java` para gestionar la conexión con la base de datos.
- **`controllers/`**: Contiene los controladores que gestionan las interacciones entre las vistas y la lógica del negocio.
- **`dao/`**: Contiene las clases DAO (Data Access Object) que permiten interactuar con la base de datos.
- **`model/`**: Contiene las clases modelo que representan los datos del sistema (Deporte, Deportista, Equipo, etc.).
- **`utils/`**: Contiene utilidades como la clase `Propiedades.java` para manejar configuraciones y propiedades.
- **`fxml/`**: Contiene los archivos FXML que definen la estructura de las vistas de la interfaz gráfica.
- **`idiomas/`**: Contiene archivos de propiedades para la traducción y adaptación del proyecto a diferentes idiomas.
- **`images/`**: Contiene las imágenes utilizadas en la interfaz de usuario.
- **`sql/`**: Contiene scripts SQL para la creación de la base de datos.
- **`css/`**: Contiene el archivo de estilo CSS para la personalización visual de la interfaz.

### 2. **Dependencias:**
- **JavaFX**: Utilizado para la creación de la interfaz gráfica.
- **SQLite / MySQL**: Base de datos utilizada para almacenar la información.
- **Propiedades multilingües**: El proyecto es multilingüe, soportando inglés, español y euskera.

### 3. **Funciones Principales:**
- **Agregar Deporte**: Permite agregar nuevos deportes a la base de datos.
- **Agregar Deportista**: Permite agregar nuevos deportistas.
- **Agregar Equipo**: Permite agregar equipos que participarán en eventos.
- **Agregar Evento**: Permite agregar nuevos eventos a las olimpiadas.
- **Agregar Olimpiada**: Permite agregar nuevos eventos olímpicos.
- **Agregar Participación**: Permite agregar participaciones de los deportistas en las olimpiadas.
- **Visualización**: Permite visualizar y modificar los datos de deportes, deportistas, equipos, eventos, olimpiadas y participaciones.

### 4. **Interfaz Gráfica:**
El proyecto utiliza JavaFX para crear una interfaz gráfica de usuario intuitiva que permite gestionar los datos de los deportistas y las olimpiadas. La interfaz incluye vistas como:
- **Menú Principal**: Ofrece acceso a todas las funcionalidades principales del sistema.
- **Formulario de Entrada**: Para agregar nuevos datos.
- **Formulario de Edición**: Para editar los datos existentes.

### 5. **Archivos Importantes:**
- **`configuration.properties`**: Contiene configuraciones básicas como la conexión a la base de datos.
- **`olimpiadas.sql`**: Script SQL para crear la base de datos y las tablas necesarias.
- **`messages_*.properties`**: Archivos de propiedades para la gestión de idiomas (inglés, español, euskera).
- **`MenuPrincipal.fxml`**: Archivo FXML para el menú principal de la aplicación.
- **`application.css`**: Archivo CSS para personalizar el diseño de la interfaz.

### 6. **Cómo Ejecutar el Proyecto:**

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/andreatticj/proyecto1_dein.git

    Configura la base de datos:
        Asegúrate de tener instalada y configurada una base de datos compatible (SQLite o MySQL).
        Ejecuta el script sql/olimpiadas.sql para crear las tablas necesarias.

    Compila y ejecuta el proyecto:
        Abre el proyecto en tu IDE favorito (IntelliJ IDEA recomendado).
        Asegúrate de tener las dependencias de JavaFX configuradas correctamente.
        Ejecuta la clase principal MenuPrincipal.java.
   
    Configura la base de datos:

    Asegúrate de tener instalada y configurada una base de datos compatible (SQLite o MySQL).
    Ejecuta el script sql/olimpiadas.sql para crear las tablas necesarias.

### Compila y ejecuta el proyecto:

    Abre el proyecto en tu IDE favorito (IntelliJ IDEA recomendado).
    Asegúrate de tener las dependencias de JavaFX configuradas correctamente.
    Ejecuta la clase principal MenuPrincipal.java.