
package utilidades;

import java.sql.Connection;
import java.sql.Statement;
import modelo.MetodoPagoDAO;

public class CrearTablas {
    
    public static void crearEstructuraInicial() {
        String[] tablas = {            
            """
            CREATE TABLE IF NOT EXISTS Usuario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                contraseña TEXT NOT NULL
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS Proveedor (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                email TEXT UNIQUE NOT NULL,
                telefono TEXT NOT NULL,
                activo BOOLEAN DEFAULT 1,
                usuarioId INTEGER NOT NULL,
                FOREIGN KEY (usuarioId) REFERENCES Usuario(id) ON DELETE CASCADE
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS Cliente (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                email TEXT UNIQUE NOT NULL,
                telefono TEXT NOT NULL,
                activo BOOLEAN DEFAULT 1,
                usuarioId INTEGER NOT NULL,
                FOREIGN KEY (usuarioId) REFERENCES Usuario(id) ON DELETE CASCADE
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS Producto (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                activo BOOLEAN DEFAULT 1,
                usuarioId INTEGER NOT NULL,
                FOREIGN KEY (usuarioId) REFERENCES Usuario(id) ON DELETE CASCADE
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS MetodoPago (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL UNIQUE
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS Transaccion (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                fecha DATE NOT NULL,
                proveedorId INTEGER NOT NULL,
                clienteId INTEGER NOT NULL,
                metodopagoId INTEGER NOT NULL,
                productoId INTEGER NOT NULL,
                montoTotal REAL NOT NULL CHECK (montoTotal >= 0),
                porcentajeProveedor REAL NOT NULL CHECK (porcentajeProveedor >= 0 AND porcentajeProveedor <= 100),
                porcentajeCliente REAL NOT NULL CHECK (porcentajeCliente >= 0 AND porcentajeCliente <= 100),
                usuarioId INTEGER NOT NULL,
                FOREIGN KEY (proveedorId) REFERENCES Proveedor(id) ON DELETE RESTRICT,
                FOREIGN KEY (clienteId) REFERENCES Cliente(id) ON DELETE RESTRICT,
                FOREIGN KEY (metodopagoId) REFERENCES MetodoPago(id) ON DELETE RESTRICT,
                FOREIGN KEY (productoId) REFERENCES Producto(id) ON DELETE RESTRICT,
                FOREIGN KEY (usuarioId) REFERENCES Usuario(id) ON DELETE CASCADE
            )   
            """ 
        };

        try (Connection con = ConexionBDInterManager.obtenerConexion(); Statement stmt = con.createStatement()) {
            for (String tablaSQL : tablas) {
                stmt.executeUpdate(tablaSQL);
            }
            System.out.println("Todas las tablas han sido creadas correctamente.");
            
        } catch (Exception e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
       }
        
        // Insertamos en la tabla MetodoPago los registros predeterminados
        MetodoPagoDAO mpDAO = new MetodoPagoDAO();
        mpDAO.insertarSiNoExiste("Efectivo");
        mpDAO.insertarSiNoExiste("Tarjeta");
        mpDAO.insertarSiNoExiste("Transferencia");
        mpDAO.insertarSiNoExiste("Otros");
        
    }
}


