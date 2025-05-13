package co.edu.poli.proyectotienda.strategy;

public interface EstrategiaPago {
    boolean procesar(double monto);
    String getNombre();
}
