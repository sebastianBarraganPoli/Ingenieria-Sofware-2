package co.edu.poli.proyectotienda.modelo;

public class EnvioGratis extends CarritoDecorador {
    public EnvioGratis(Carrito carrito) {
        super(carrito);
    }

    @Override
    public String getDescripcion() {
        return carrito.getDescripcion() + " 🚚 + Envío Gratis";
    }

    @Override
    protected double modificarTotal() {
        return carrito.getTotal(); // NO AÑADIR COSTO EXTRA
    }
}
