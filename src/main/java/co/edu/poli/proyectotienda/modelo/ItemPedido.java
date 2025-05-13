package co.edu.poli.proyectotienda.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ItemPedido {
    private Producto producto;
    private final IntegerProperty cantidad = new SimpleIntegerProperty();

    public ItemPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad.set(cantidad);
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * getCantidad();
    }

    @Override
    public String toString() {
        return producto.getNombre() + " x " + getCantidad() + " = $" + calcularSubtotal();
    }
}
