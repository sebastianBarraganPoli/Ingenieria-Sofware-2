package co.edu.poli.proyectotienda.modelo;

public class Descuento extends CarritoDecorador {

    private double porcentajeDescuento;

    public Descuento(Carrito carrito, double porcentajeDescuento) {
        super(carrito);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    protected double modificarTotal() {
        return carrito.getTotal() * (1 - porcentajeDescuento / 100.0);
    }

    @Override
    public String getDescripcion() {
        return carrito.getDescripcion() + " 💰 + Descuento del " + porcentajeDescuento + "%";
    }
}

