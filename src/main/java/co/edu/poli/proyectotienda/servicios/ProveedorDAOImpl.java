package co.edu.poli.proyectotienda.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.edu.poli.proyectotienda.modelo.Proveedor;

public class ProveedorDAOImpl implements DAO{

    private Connection conexion;

    public ProveedorDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    public ProveedorDAOImpl() throws SQLException {
        this.conexion = ConexionDB.getInstancia().getConexion();
    }

    @Override
    public String create(Object o) {
        if (o instanceof Proveedor) {
            Proveedor proveedor = (Proveedor) o;
            String sql = "INSERT INTO proveedores (nombre, certificacion, evaluacion, politica) VALUES (?,?,?,?)";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, proveedor.getNombre());
                stmt.setString(2, proveedor.getCertificacion().getTipo());
                stmt.setInt(3, proveedor.getEvaluacion().getPuntaje());
                stmt.setString(4, proveedor.getPoliticaEntrega().getTipo());

                stmt.executeUpdate();
                return "Proveedor guardado correctamente en la base de datos.";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error al agregar el Proveedor.";
            }
        }
        return "Objeto inválido.";
    }

    @Override
    public List<Object> list() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public Object read(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public String update(String id, Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public String delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    

}
