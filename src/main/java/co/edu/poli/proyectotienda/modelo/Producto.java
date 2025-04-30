package co.edu.poli.proyectotienda.modelo;

public class Producto implements Observador {
    private String nombre;
    private double precio;
    private double precioAnterior;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.precioAnterior = precio;
    }

    @Override
    public void update(double porcentaje) {
        precioAnterior = precio;
        precio += precio * porcentaje / 100.0;
    }

    public String getDetalles() {
        return String.format("🛒 %s: Antes: $%.2f → Ahora: $%.2f", nombre, precioAnterior, precio);
    }
}
