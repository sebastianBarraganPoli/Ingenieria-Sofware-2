package co.edu.poli.proyectotienda.modelo;

public class PagoAdapterNequi implements Pago {
    private PagoNequi pagoNequi;
    
    public PagoAdapterNequi(PagoNequi pagoNequi) {
        this.pagoNequi = pagoNequi;
    }
    
    @Override
    public String procesarPago(double monto) {
        return pagoNequi.procesarPago(monto);
    }
}