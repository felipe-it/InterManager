# InterManager

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![SQLite](https://img.shields.io/badge/sqlite-%2307405e.svg?style=for-the-badge&logo=sqlite&logoColor=white)
![NetBeans IDE](https://img.shields.io/badge/NetBeans%20IDE-1B6AC6.svg?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)

---

## **📌 Descripción**

**InterManager** es una aplicación de escritorio desarrollada en Java pensada para intermediarios que trabajan de forma habitual con los mismos clientes y proveedores. Permite gestionar de manera sencilla las operaciones de intermediación entre compradores y vendedores, especialmente en casos donde las transacciones se repiten con frecuencia, como ocurre con intermediarios de materias primas u otros servicios.

Mi idea principal es que esta herramienta facilite la gestión administrativa del negocio y además proporcione datos valiosos para mejorar la toma de decisiones estratégicas.

Problema: Intermediarios que carecen de un sistema eficiente y accesible para la organización de su trabajo.

Oportunidad: Crear una plataforma intuitiva que simplifique las gestiones de sus actividades profesionales.

---

## **🚀** Funcionalidades principales

- Gestión de Usuarios
    - Registro con nombre, email y contraseña.
    - Iniciar sesión con email y contraseña.
    - Modificar información (nombre, email y contraseña).
    - Eliminar cuenta.
- Gestión de Proveedores, Clientes, Productos y Transacciones
    - Visualizar las instancias en una tabla.
    - Filtrar y ordenar los datos.
    - Crear nuevos registros.
    - Editar la información de un registro existente.
    - Eliminar un registro.
    - Exportar la información de la tabla con el formato CSV.
- Visualización de Reportes
    - Datos Globales
        - Cantidad total de transacciones registradas.
        - Ingresos totales del usuario.
        - Ingresos totales generados por los proveedores.
        - Ingresos totales generados por los clientes.
        - Gráfico con la evolución de ingresos en los últimos 30 días.
    - Datos de Proveedores, Clientes, Productos (en forma de tablas y gráficas)
        - Cantidad total de transacciones en las que participa
        - Suma del monto total de las transacciones en las que interviene
        - Suma de ingresos generados por comisiones

---

## **🛠 Tecnologías utilizadas**

- Java
- Maven
- Swing
- SQLite
- JDBC

---

## 🏗 Arquitectura

El proyecto sigue el patrón **MVC (Model–View–Controller)**, separando:

- **Model**: entidades y acceso a datos (SQLite + JDBC)
- **View**: interfaz gráfica (Swing)
- **Controller**: lógica de negocio y coordinación

---

## ⚙️ Guía de Instalación y Ejecución

### 📋 Requisitos Previos

Para ejecutar este proyecto, necesitas tener instalado:

1. **Java JDK 20** o superior.
2. **Git** (solo desarrolladores).
3. **Apache Maven 3.x** (si deseas ejecutar desde terminal).

> Verificación: Puedes comprobar tus versiones abriendo una terminal y escribiendo:
java -version y mvn -version.
> 

---

### Ejecución desde Terminal

1. Clonar el repositorio

`git clone https://github.com/felipe-it/InterManager.git`

2. Compilar, empaquetar y ejecutar

`mvn clean package && java -jar target/InterManager-1.0-SNAPSHOT-jar-with-dependencies.jar`

---

### Ejecución desde IDE

1. Clonar el repositorio
`git clone https://github.com/felipe-it/InterManager.git`

2. Abrir Proyecto: Seleccionar "Open Project" y buscar InterManager.

3. Ejecutar: Haz clic derecho sobre el proyecto y selecciona **Run**.

---

### Ejecución desde Release (para usuarios finales)

1. Ve a la sección de **Releases** de este repositorio.
2. Descarga el archivo `InterManager.jar`.
3. Ejecuta el archivo

---