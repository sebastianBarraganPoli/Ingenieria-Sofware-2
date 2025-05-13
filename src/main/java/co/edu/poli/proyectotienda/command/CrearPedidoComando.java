package co.edu.poli.proyectotienda.command;

import co.edu.poli.proyectotienda.modelo.Pedido;
import co.edu.poli.proyectotienda.service.GestorPedidos;

public class CrearPedidoComando implements Comando {
    private Pedido pedido;
    private GestorPedidos gestor;

    public CrearPedidoComando(Pedido pedido, GestorPedidos gestor) {
        this.pedido = pedido;
        this.gestor = gestor;
    }

    @Override
    public boolean ejecutar() {
        return gestor.agregarPedido(pedido);
    }

    @Override
    public boolean deshacer() {
        return gestor.eliminarPedido(pedido.getId());
    }
}
