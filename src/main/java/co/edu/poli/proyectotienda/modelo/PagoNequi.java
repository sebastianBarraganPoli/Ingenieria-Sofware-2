package co.edu.poli.proyectotienda.modelo;

public class PagoNequi implements Pago {
    @Override
    public String procesarPago(double monto) {
        return "Pago realizado con Nequi por: " + monto;
    }
}
