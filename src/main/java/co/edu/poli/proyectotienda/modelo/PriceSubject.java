package co.edu.poli.proyectotienda.modelo;

import java.util.ArrayList;
import java.util.List;

public class PriceSubject implements Subject {
    private List<Observador> observers = new ArrayList<>();

    @Override
    public void register(Observador o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observador o) {
        observers.remove(o);
    }

    @Override
    public void notifyAllObservers(double porcentaje) {
        for (Observador o : observers) {
            o.update(porcentaje);
        }
    }
}
