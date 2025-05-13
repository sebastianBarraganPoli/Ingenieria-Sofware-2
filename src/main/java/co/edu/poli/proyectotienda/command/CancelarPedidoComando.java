package co.edu.poli.proyectotienda.command;

import co.edu.poli.proyectotienda.modelo.Pedido;
import co.edu.poli.proyectotienda.service.GestorPedidos;

public class CancelarPedidoComando implements Comando {
    private Pedido pedido;
    private GestorPedidos gestor;
    private Pedido.EstadoPedido estadoAnterior;

    public CancelarPedidoComando(int idPedido, GestorPedidos gestor) {
        this.gestor = gestor;
        this.pedido = gestor.buscarPedido(idPedido);
        if (this.pedido != null) {
            this.estadoAnterior = this.pedido.getEstado();
        }
    }

    @Override
    public boolean ejecutar() {
        if (pedido == null) {
            return false;
        }
        pedido.setEstado(Pedido.EstadoPedido.CANCELADO);
        return gestor.actualizarPedido(pedido);
    }

    @Override
    public boolean deshacer() {
        if (pedido == null) {
            return false;
        }
        pedido.setEstado(estadoAnterior);
        return gestor.actualizarPedido(pedido);
    }
}
