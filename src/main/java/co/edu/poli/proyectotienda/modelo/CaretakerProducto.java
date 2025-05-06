package co.edu.poli.proyectotienda.modelo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class CaretakerProducto {
    private Map<Producto, Deque<MementoProducto>> historial = new HashMap<>();

    public void guardarEstado(Producto producto, int anio) {
        Deque<MementoProducto> stack = historial.computeIfAbsent(producto, p -> new ArrayDeque<>());
        stack.push(producto.crearMemento(anio));
    }

    public void restaurarEstado(Producto producto) {
        Deque<MementoProducto> stack = historial.get(producto);
        if (stack != null && !stack.isEmpty()) {
            MementoProducto m = stack.pop();
            producto.restaurarDesdeMemento(m);
        }
    }

    public MementoProducto obtenerPrecioPorAnio(Producto producto, int anio) {
        Deque<MementoProducto> stack = historial.get(producto);
        if (stack != null) {
            for (MementoProducto m : stack) {
                if (m.getAnio() == anio) {
                    return m;
                }
            }
        }
        return null;
    }
}
