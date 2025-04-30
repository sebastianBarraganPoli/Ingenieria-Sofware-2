package co.edu.poli.proyectotienda.modelo;

public interface Subject {
    void register(Observador o);
    void unregister(Observador o);
    void notifyAllObservers(double porcentaje);
}
