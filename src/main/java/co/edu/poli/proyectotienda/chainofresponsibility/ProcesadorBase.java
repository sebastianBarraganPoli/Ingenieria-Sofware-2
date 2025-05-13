package co.edu.poli.proyectotienda.chainofresponsibility;

import co.edu.poli.proyectotienda.modelo.Pedido;

public abstract class ProcesadorBase implements ProcesadorPedido {
    protected ProcesadorPedido siguiente;

    @Override
    public void setSiguiente(ProcesadorPedido procesador) {
        this.siguiente = procesador;
    }

    protected boolean procesarSiguiente(Pedido pedido) {
        if (siguiente != null) {
            return siguiente.procesarPedido(pedido);
        }
        return true;
    }
}
