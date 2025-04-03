package co.edu.poli.proyectotienda.modelo;

public class CarritoVip extends CarritoDecorador{

    private double descuentoVip;
    
    public CarritoVip(Carrito carrito, double descuentoVip) {
        super(carrito);
        this.descuentoVip = descuentoVip;
    }

    @Override
    protected double modificarTotal() {
        return carrito.getTotal() * (1 - descuentoVip / 100.0);
    }

    @Override
    public String getDescripcion() {
        return carrito.getDescripcion() + " 💰 + Descuento Vip " + descuentoVip + "%";
    }
    
}
