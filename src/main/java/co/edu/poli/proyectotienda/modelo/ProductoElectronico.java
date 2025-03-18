package co.edu.poli.proyectotienda.modelo;

public class ProductoElectronico extends Producto implements Prototype{

	private int voltajeEntrada;

	public ProductoElectronico(String id, String descripcion, double precio, int voltajeEntrada) {
		super(id, descripcion, precio);
		this.voltajeEntrada = voltajeEntrada;
	}
	
	
	public int getVoltajeEntrada() {
		return voltajeEntrada;
	}


	public void setVoltajeEntrada(int voltajeEntrada) {
		this.voltajeEntrada = voltajeEntrada;
	}


	@Override
	public Prototype clonar() {
	    return new ProductoElectronico(this.getId(), this.getDescripcion(), this.getPrecio(), this.voltajeEntrada);
	}

}
