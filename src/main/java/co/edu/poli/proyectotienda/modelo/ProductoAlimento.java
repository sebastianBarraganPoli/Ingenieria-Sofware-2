package co.edu.poli.proyectotienda.modelo;

public class ProductoAlimento extends Producto implements Prototype{

	private int aporteCalorico;

	public ProductoAlimento(String id, String descripcion, double precio, int aporteCalorico) {
		super(id, descripcion, precio);
		this.aporteCalorico = aporteCalorico;
	}
	
	
	
	public int getAporteCalorico() {
		return aporteCalorico;
	}



	public void setAporteCalorico(int aporteCalorico) {
		this.aporteCalorico = aporteCalorico;
	}



	@Override
	public Prototype clonar() {
	    return new ProductoAlimento(this.getId(), this.getDescripcion(), this.getPrecio(), this.aporteCalorico);
	}


}
