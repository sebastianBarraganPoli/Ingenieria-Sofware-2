package co.edu.poli.proyectotienda.modelo;

public class Puntos extends CarritoDecorador{
    private static final double tasaPuntos = 0.1; // 1 punto por cada $10 gastados

    public Puntos(Carrito carrito) {
        super(carrito);
    }

    @Override
    protected double modificarTotal() {
        return carrito.getTotal(); // No cambia el total
    }

    public int getPuntos() {
        return (int) (carrito.getTotal() * tasaPuntos);
    }

    @Override
    public String getDescripcion() {
        return carrito.getDescripcion() + " 🏆 + " + getPuntos() + " puntos acumulados.";
    }
}
