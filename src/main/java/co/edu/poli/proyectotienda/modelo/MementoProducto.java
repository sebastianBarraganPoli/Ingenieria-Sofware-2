package co.edu.poli.proyectotienda.modelo;

public class MementoProducto {
    private final double precio;
    private final int anio;

    public MementoProducto(double precio, int anio) {
        this.precio = precio;
        this.anio = anio;
    }

    public double getPrecio() {
        return precio;
    }

    public int getAnio() {
        return anio;
    }
}
