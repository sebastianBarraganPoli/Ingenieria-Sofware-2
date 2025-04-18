package co.edu.poli.proyectotienda.modelo;

public class ProductoReal implements Producto {
    
    private String nombre;
    private double precio;

    public ProductoReal(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String mostrarDetalles() {
        return "🛍️ Producto: " + nombre + ", Precio: $" + precio;
    }
}
