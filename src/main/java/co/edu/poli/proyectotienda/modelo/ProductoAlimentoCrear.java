package co.edu.poli.proyectotienda.modelo;

public class ProductoAlimentoCrear implements ProductoFactory {

	@Override
	public Producto crearProducto(Producto producto) {
        if (producto instanceof ProductoAlimento) {
            return producto;
        }
        throw new IllegalArgumentException("Producto no válido para ProductoAlimentoCrear");
    }
}
