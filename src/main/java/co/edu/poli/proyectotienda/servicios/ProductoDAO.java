package co.edu.poli.proyectotienda.servicios;

import java.util.List;

public interface ProductoDAO extends DAO{

	public List<Object> buscarPorPrecio(double precioMin, double precioMax);
}
