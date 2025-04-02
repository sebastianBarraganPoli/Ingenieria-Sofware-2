package co.edu.poli.proyectotienda.modelo;

public abstract class CarritoDecorador implements Carrito {
    protected Carrito carrito;

    public CarritoDecorador(Carrito carrito) {
        this.carrito = carrito;
    }

    @Override
    public String getDescripcion() {
        return carrito.getDescripcion();
    }

    @Override
    public double getTotal() {
        return modificarTotal();
    }

    // Método abstracto que cada decorador deberá implementar
    protected abstract double modificarTotal();
}