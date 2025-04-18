package co.edu.poli.proyectotienda.modelo;

public class ClienteFacade {

    private GestionCliente gestionCliente;
    private GestionPedidos gestionPedidos;
    private GestionFormasPago gestionFormasPago;

    // Constructor que recibe las instancias desde fuera
    public ClienteFacade(GestionCliente gestionCliente, GestionPedidos gestionPedidos, GestionFormasPago gestionFormasPago) {
        this.gestionCliente = gestionCliente;
        this.gestionPedidos = gestionPedidos;
        this.gestionFormasPago = gestionFormasPago;
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
