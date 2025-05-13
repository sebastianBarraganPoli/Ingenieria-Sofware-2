package co.edu.poli.proyectotienda.command;

import co.edu.poli.proyectotienda.modelo.Pedido;
import co.edu.poli.proyectotienda.service.GestorPedidos;

public class ModificarPedidoComando implements Comando {
    private Pedido pedidoOriginal;
    private Pedido pedidoModificado;
    private GestorPedidos gestor;

    public ModificarPedidoComando(Pedido pedidoModificado, GestorPedidos gestor) {
        this.pedidoModificado = pedidoModificado;
        this.gestor = gestor;
        this.pedidoOriginal = gestor.buscarPedido(pedidoModificado.getId());
    }

    @Override
    public boolean ejecutar() {
        return gestor.actualizarPedido(pedidoModificado);
    }

    @Override
    public boolean deshacer() {
        return gestor.actualizarPedido(pedidoOriginal);
    }
}
