package co.edu.poli.proyectotienda.strategy;

public class PagoTarjeta implements EstrategiaPago {
    private String numeroTarjeta;
    private String fechaExp;

    public PagoTarjeta(String numeroTarjeta, String fechaExp) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExp = fechaExp;
    }

    @Override
    public boolean procesar(double monto) {
        // Simulación de procesamiento de pago con tarjeta
        System.out.println("Procesando pago con tarjeta: " + numeroTarjeta + " por $" + monto);
        return true;
    }

    @Override
    public String getNombre() {
        return "Tarjeta terminada en " + numeroTarjeta.substring(numeroTarjeta.length() - 4);
    }
}

