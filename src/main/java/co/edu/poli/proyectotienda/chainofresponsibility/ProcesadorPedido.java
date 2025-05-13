package co.edu.poli.proyectotienda.chainofresponsibility;

import co.edu.poli.proyectotienda.modelo.Pedido;

public interface ProcesadorPedido {
    boolean procesarPedido(Pedido pedido);
    void setSiguiente(ProcesadorPedido procesador);
}
