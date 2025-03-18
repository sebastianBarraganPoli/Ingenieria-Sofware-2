package co.edu.poli.proyectotienda.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.proyectotienda.modelo.Cliente;

public class ClienteDAOImpl implements DAO{

	private Connection conexion;
    private List<Cliente> clientes = new ArrayList<>();
	
    public ClienteDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    // Puedes conservar el constructor sin parámetros si lo necesitas
    public ClienteDAOImpl() throws SQLException {
        this.conexion = ConexionDB.getInstancia().getConexion();
    }
	
    @Override
    public String create(Object o) {
        if (o instanceof Cliente) {
            Cliente cliente = (Cliente) o;
            String sql = "INSERT INTO clientes (id, nombre) VALUES (?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, cliente.getId());
                stmt.setString(2, cliente.getNombre());
                stmt.executeUpdate();
                return "Cliente agregado correctamente.";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error al agregar el cliente.";
            }
        }
        return "Objeto inválido.";
    }

    @Override
    public List<Object> list() {
        List<Object> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("id"),
                    rs.getString("nombre")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Object read(String id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getString("id"),
                    rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Cliente no encontrado.";
    }

    @Override
    public String update(String id, Object o) {
        if (o instanceof Cliente) {
            Cliente cliente = (Cliente) o;
            String sql = "UPDATE clientes SET nombre = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, cliente.getNombre());
                stmt.setString(2, id);
                int rows = stmt.executeUpdate();
                return (rows > 0) ? "Cliente actualizado correctamente." : "Cliente no encontrado.";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error al actualizar el cliente: " + e.getMessage();
            }
        }
        return "Objeto inválido.";
    }

    @Override
    public String delete(String id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rows = stmt.executeUpdate();
            return (rows > 0) ? "Cliente eliminado correctamente." : "Cliente no encontrado.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al eliminar el cliente: " + e.getMessage();
        }
    }
}
