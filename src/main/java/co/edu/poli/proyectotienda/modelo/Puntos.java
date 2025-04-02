package co.edu.poli.proyectotienda.modelo;

public class Puntos extends CarritoDecorador{
    private static final double tasa_puntos = 0.1; // 1 punto por cada $10 gastados

    public Puntos(Carrito carrito) {
        super(carrito);
    }

    @Override
    protected double modificarTotal() {
        return carrito.getTotal(); // Los puntos no afectan al total
    }

    public int getPuntos() {
        return (int) (carrito.getTotal() * tasa_puntos);
    }

    @Override
    public String getDescripcion() {
        return carrito.getDescripcion() + " + Programa de Puntos";
    }
}
