package co.edu.poli.proyectotienda.modelo;

public class GestionPedidos {
    public String mostrarHistorial() {
        return "📦 Historial: Pedido #123, Pedido #456";
    }

    public String realizarPedido(String pedido) {
        return "🛒 Pedido realizado: " + pedido;
    }
}
