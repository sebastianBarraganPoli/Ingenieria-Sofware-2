package co.edu.poli.proyectotienda.chainofresponsibility;

import co.edu.poli.proyectotienda.modelo.Pedido;

public class NotificadorPedido extends ProcesadorBase {
    @Override
    public boolean procesarPedido(Pedido pedido) {
        System.out.println("Enviando notificación para pedido #" + pedido.getId());
        
        // Simular envío de email al cliente
        System.out.println("Email enviado a " + pedido.getCliente().getEmail() + 
                           " - Su pedido #" + pedido.getId() + " ha sido procesado");
        
        pedido.setEstado(Pedido.EstadoPedido.ENVIADO);
        System.out.println("Notificación enviada correctamente");
        
        return procesarSiguiente(pedido);
    }
}

