package co.edu.poli.proyectotienda.modelo;

public class EnvioGratis extends CarritoDecorador {
    private static final double costo_envio = 10.0;

    public EnvioGratis(Carrito carrito) {
        super(carrito);
    }
    @Override
    public String getDescripcion() {
        return carrito.getDescripcion() + " + Envío Gratis";
    }

    public double getTotal() {
        return modificarTotal();
    }
    
    @Override
    protected double modificarTotal() {
        double total = carrito.getTotal();
        return total + costo_envio;
    }
}
