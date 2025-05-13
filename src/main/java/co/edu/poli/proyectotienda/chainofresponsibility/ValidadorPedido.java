package co.edu.poli.proyectotienda.chainofresponsibility;

import co.edu.poli.proyectotienda.modelo.Pedido;

public class ValidadorPedido extends ProcesadorBase {
    @Override
    public boolean procesarPedido(Pedido pedido) {
        System.out.println("Validando pedido #" + pedido.getId());
        
        // Verificar que el pedido tenga items
        if (pedido.getItems().isEmpty()) {
            System.out.println("Error: El pedido no tiene items");
            return false;
        }
        
        // Verificar que el cliente exista
        if (pedido.getCliente() == null) {
            System.out.println("Error: El pedido no tiene cliente asignado");
            return false;
        }
        
        // Verificar que tenga método de pago
        if (pedido.getEstrategiaPago() == null) {
            System.out.println("Error: El pedido no tiene método de pago");
            return false;
        }
        
        pedido.setEstado(Pedido.EstadoPedido.VALIDADO);
        System.out.println("Pedido validado correctamente");
        
        return procesarSiguiente(pedido);
    }
}
