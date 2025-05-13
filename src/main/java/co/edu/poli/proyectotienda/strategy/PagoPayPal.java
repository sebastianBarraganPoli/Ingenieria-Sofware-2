package co.edu.poli.proyectotienda.strategy;

public class PagoPayPal implements EstrategiaPago {
    private String email;

    public PagoPayPal(String email) {
        this.email = email;
    }

    @Override
    public boolean procesar(double monto) {
        // Simulación de procesamiento de pago con PayPal
        System.out.println("Procesando pago con PayPal: " + email + " por $" + monto);
        return true;
    }

    @Override
    public String getNombre() {
        return "PayPal (" + email + ")";
    }
}
