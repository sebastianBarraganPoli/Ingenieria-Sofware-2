package co.edu.poli.proyectotienda.modelo;

public class GestionFormasPago {
    public String mostrarFormasPago() {
        return "💳 Formas de pago: Tarjeta, Nequi";
    }

    public String activarFormaPago(String forma) {
        return "✅ Activada forma de pago: " + forma;
    }

    public String bloquearFormaPago(String forma) {
        return "⛔ Bloqueada forma de pago: " + forma;
    }
}
