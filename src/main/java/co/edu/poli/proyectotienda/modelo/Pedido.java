package co.edu.poli.proyectotienda.modelo;

import java.util.List;

public class Pedido {
	public Pedido(String numero, String fecha, Cliente cliente, List<Producto> producto) {
		super();
		this.numero = numero;
		this.fecha = fecha;
		this.cliente = cliente;
		this.producto = producto;
	}

    public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Pedido [numero=" + numero + ", fecha=" + fecha + ", cliente=" + cliente + ", producto=" + producto
				+ "]";
	}

	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}

    /**
     * 
     */
    private String numero;

    /**
     * 
     */
    private String fecha;

    /**
     * 
     */
    private Cliente cliente;

    /**
     * 
     */
    private List <Producto> producto;

    /**
     * 
     */
    public void Operation1() {
        // TODO implement here
    }
}
