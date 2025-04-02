package co.edu.poli.proyectotienda.modelo;

public class Descuento extends CarritoDecorador {

    private double porcentaje_descuento;

    public Descuento(Carrito carrito, double porcentaje_descuento) {
        super(carrito);
        this.porcentaje_descuento = porcentaje_descuento;
    }

    @Override
    protected double modificarTotal() {
        return getDescuento(carrito.getTotal());
    }

    public double getDescuento(double amount) {
        return amount * (1 - porcentaje_descuento / 100.0);
    }

    @Override
    public String getDescripcion() {
        return carrito.getDescripcion() + " + Descuento " + porcentaje_descuento + "%";
    }
}

