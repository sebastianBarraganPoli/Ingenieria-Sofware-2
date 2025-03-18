package co.edu.poli.proyectotienda.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO{

	private Connection conexion;
    /**
     * Default constructor
     */
    public ProductoDAOImpl() throws SQLException {
    	this.conexion = ConexionDB.getInstancia().getConexion();
    }

	@Override
	public String create(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(String id, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public List<Object> buscarPorPrecio(double precioMin, double precioMax) {
        List<Object> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE precio BETWEEN ? AND ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDouble(1, precioMin);
            stmt.setDouble(2, precioMax);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productos.add(show(rs)); // Usa el método create para crear el objeto adecuado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
	
	private String show(ResultSet rs) throws SQLException {
	    return "ID: " + rs.getString("id") +
	           ", Nombre: " + rs.getString("descripcion") +
	           ", Precio: " + rs.getDouble("precio");
	}
}
