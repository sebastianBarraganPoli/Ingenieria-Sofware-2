package co.edu.poli.proyectotienda.modelo;

public class Producto implements Observador {
    private String nombre;
    private double precio;
    private double precioAnterior;
    private HistorialProducto historial = new HistorialProducto();

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.precioAnterior = precio;
    }

    public void guardarPrecio() {
        historial.guardarEstado(new MementoProducto(precio));
    }

    public void restaurarPrecio() {
        MementoProducto memento = historial.restaurarEstado();
        if (memento != null) {
            this.precio = memento.getPrecio();
        }
    }

    @Override
    public void update(double porcentaje) {
        guardarPrecio(); // Guardamos antes de modificar
        precioAnterior = precio;
        precio += precio * porcentaje / 100.0;
    }

    public String getDetalles() {
        return String.format("🛒 %s: Antes: $%.2f → Ahora: $%.2f", nombre, precioAnterior, precio);
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
