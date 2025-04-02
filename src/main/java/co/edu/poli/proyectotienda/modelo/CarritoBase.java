package co.edu.poli.proyectotienda.modelo;

import java.util.ArrayList;
import java.util.List;

class CarritoBase implements Carrito{
    private double total;
    private List<Producto> items = new ArrayList<>();

    public CarritoBase(double total) {
        this.total = total;
    }

    @Override
    public String getDescripcion() {
        return "Carrito de compras";
    }
    public void agregarItem(Producto item) {
        items.add(item);
        total += item.getPrecio();
    }
    @Override
    public double getTotal() {
        return total;
    }
    public List<Producto> getItems() {
        return new ArrayList<>(items);
    }
}
