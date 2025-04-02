package co.edu.poli.proyectotienda.modelo;

public class PagoAdapterPaypal implements Pago {
    private PagoPaypal pagoPaypal;
    
    public PagoAdapterPaypal(PagoPaypal pagoPaypal) {
        this.pagoPaypal = pagoPaypal;
    }
    
    @Override
    public String procesarPago(double monto) {
        return pagoPaypal.realizarPago(monto);
    }
}
