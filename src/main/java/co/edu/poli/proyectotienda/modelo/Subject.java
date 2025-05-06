package co.edu.poli.proyectotienda.modelo;

public interface Subject {
    public void register(Observador o);
    public void unregister(Observador o);
    public void notifyAllObservers(double porcentaje);
}
