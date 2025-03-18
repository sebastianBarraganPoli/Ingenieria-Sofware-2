package co.edu.poli.proyectotienda.modelo;

public abstract class Producto {
	/**
     * 
     */
    private String id;

    public Producto(String id, String descripcion, double precio) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
	}
    
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	

	@Override
	public String toString() {
		return "Producto [id=" + id + ", descripcion=" + descripcion + ", precio=" + precio + "]";
	}

	/**
     * 
     */
    private String descripcion;

    /**
     * 
     */
    private double precio;
}
