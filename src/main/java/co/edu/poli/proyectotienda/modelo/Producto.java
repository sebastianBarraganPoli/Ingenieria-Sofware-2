package co.edu.poli.proyectotienda.modelo;

public class Producto implements Observador {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public void update(double porcentaje) {
        precio += precio * porcentaje / 100.0;
    }

    // Métodos de Memento
    public MementoProducto crearMemento(int anio) {
        return new MementoProducto(precio, anio);
    }
    
    public void restaurarDesdeMemento(MementoProducto m) {
        this.precio = m.getPrecio();
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
}
