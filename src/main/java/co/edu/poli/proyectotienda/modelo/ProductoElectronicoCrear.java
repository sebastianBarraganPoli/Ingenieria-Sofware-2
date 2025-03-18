package co.edu.poli.proyectotienda.modelo;

public class ProductoElectronicoCrear implements ProductoFactory {

	public ProductoElectronicoCrear() {
		super();
	}

	@Override
	public Producto crearProducto(Producto producto) {
        if (producto instanceof ProductoElectronico) {
            return producto;
        }
        throw new IllegalArgumentException("Producto no válido para ProductoAlimentoCrear");
    }	
}
