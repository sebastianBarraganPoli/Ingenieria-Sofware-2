package co.edu.poli.proyectotienda.chainofresponsibility;

import co.edu.poli.proyectotienda.modelo.Pedido;

public class ProcesadorPago extends ProcesadorBase {
    @Override
    public boolean procesarPedido(Pedido pedido) {
        System.out.println("Procesando pago para pedido #" + pedido.getId());
        
        double total = pedido.calcularTotal();
        boolean pagoExitoso = pedido.getEstrategiaPago().procesar(total);
        
        if (!pagoExitoso) {
            System.out.println("Error: No se pudo procesar el pago");
            return false;
        }
        
        pedido.setEstado(Pedido.EstadoPedido.PAGADO);
        System.out.println("Pago procesado correctamente");
        
        return procesarSiguiente(pedido);
    }
}
