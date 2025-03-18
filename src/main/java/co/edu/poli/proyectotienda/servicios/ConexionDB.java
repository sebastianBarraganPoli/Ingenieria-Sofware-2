package co.edu.poli.proyectotienda.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ConexionDB {
    private static ConexionDB instancia;
    private Connection conexion;

    // Constructor privado para el Singleton
    private ConexionDB() throws SQLException {
        // Cargar configuración y conectar
        Map<String, String> config = cargarConfiguracion();
        conexion = DriverManager.getConnection(config.get("url"), config.get("usuario"), config.get("password"));
        System.out.println("✅ Conexión establecida correctamente.");
    }

    // Método para cargar configuración y devolver un mapa con los valores
    private Map<String, String> cargarConfiguracion() {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Map<String, String> config = new HashMap<>();
        config.put("url", bundle.getString("db.url"));
        config.put("usuario", bundle.getString("db.user"));
        config.put("password", bundle.getString("db.password"));
        return config; // Retornar el mapa con la configuración
    }

    // Método para obtener la instancia única (Singleton)
    public static ConexionDB getInstancia() throws SQLException {
        if (instancia == null) {
            synchronized (ConexionDB.class) {
                if (instancia == null) {
                    instancia = new ConexionDB();
                }
            }
        }
        return instancia;
    }

    // Método para obtener la conexión
    public Connection getConexion() {
        return conexion;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() throws SQLException {
        if (conexion != null) {
            conexion.close();
            System.out.println("❌ Conexión cerrada.");
        }
    }
}
