package co.edu.poli.proyectotienda.strategy;

public class PagoTransferencia implements EstrategiaPago {
    private String cuentaBancaria;

    public PagoTransferencia(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    @Override
    public boolean procesar(double monto) {
        // Simulación de procesamiento de pago con transferencia bancaria
        System.out.println("Procesando pago con transferencia a cuenta: " + cuentaBancaria + " por $" + monto);
        return true;
    }

    @Override
    public String getNombre() {
        return "Transferencia a cuenta " + cuentaBancaria;
    }
}

