package co.edu.poli.proyectotienda.modelo;

public class ClienteFacade {
    
    private GestionCliente gestionCliente;
    private GestionPedidos gestionPedidos;
    private GestionFormasPago gestionFormasPago;

    public ClienteFacade() {
        this.gestionCliente = new GestionCliente();
        this.gestionPedidos = new GestionPedidos();
        this.gestionFormasPago = new GestionFormasPago();
    }

    public String mostrarInfoCliente() {
        return gestionCliente.mostrarInfo();
    }

    public String actualizarInfoCliente(String nuevaInfo) {
        return gestionCliente.actualizarInfo(nuevaInfo);
    }

    public String mostrarHistorialPedidos() {
        return gestionPedidos.mostrarHistorial();
    }

    public String realizarPedido(String pedido) {
        return gestionPedidos.realizarPedido(pedido);
    }

    public String mostrarFormasPago() {
        return gestionFormasPago.mostrarFormasPago();
    }

    public String activarFormaPago(String forma) {
        return gestionFormasPago.activarFormaPago(forma);
    }

    public String bloquearFormaPago(String forma) {
        return gestionFormasPago.bloquearFormaPago(forma);
    }
}
