# InterManager

---

Gestión administrativa y Business Intelligence para intermediarios

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

## **🛠 Tecnologías utilizadas**

- Java
- Swing
- SQLite
- JDBC

## 🏗 Arquitectura

El proyecto sigue el patrón **MVC (Model–View–Controller)**, separando:

- **Model**: entidades y acceso a datos (SQLite + JDBC)
- **View**: interfaz gráfica (Swing)
- **Controller**: lógica de negocio y coordinación