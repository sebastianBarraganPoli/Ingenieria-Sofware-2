package co.edu.poli.proyectotienda.modelo;

import java.util.Stack;

public class HistorialProducto {
    private Stack<MementoProducto> historial = new Stack<>();

    public void guardarEstado(MementoProducto memento) {
        historial.push(memento);
    }

    public MementoProducto restaurarEstado() {
        if (!historial.isEmpty()) {
            return historial.pop();
        }
        return null;
    }
}
