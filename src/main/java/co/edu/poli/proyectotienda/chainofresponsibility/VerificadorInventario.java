package co.edu.poli.proyectotienda.chainofresponsibility;

import co.edu.poli.proyectotienda.modelo.ItemPedido;
import co.edu.poli.proyectotienda.modelo.Pedido;
import co.edu.poli.proyectotienda.modelo.Producto;

public class VerificadorInventario extends ProcesadorBase {
    @Override
    public boolean procesarPedido(Pedido pedido) {
        System.out.println("Verificando inventario para pedido #" + pedido.getId());
        
        for (ItemPedido item : pedido.getItems()) {
            Producto producto = item.getProducto();
            int cantidad = item.getCantidad();
            
            if (producto.getStock() < cantidad) {
                System.out.println("Error: Stock insuficiente para " + producto.getNombre());
                return false;
            }
        }
        
        // Reducir el stock
        for (ItemPedido item : pedido.getItems()) {
            Producto producto = item.getProducto();
            int cantidad = item.getCantidad();
            producto.reducirStock(cantidad);
        }
        
        System.out.println("Inventario verificado correctamente");
        return procesarSiguiente(pedido);
    }
}
