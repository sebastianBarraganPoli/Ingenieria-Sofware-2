package co.edu.poli.proyectotienda.service;

import co.edu.poli.proyectotienda.modelo.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestorPedidos {
    private ObservableList<Pedido> pedidos;
    private int nextId = 1;

    public GestorPedidos() {
        this.pedidos = FXCollections.observableArrayList();
    }

    public int getNextId() {
        return nextId++;
    }

    public boolean agregarPedido(Pedido pedido) {
        return pedidos.add(pedido);
    }

    public boolean eliminarPedido(int id) {
        return pedidos.removeIf(p -> p.getId() == id);
    }

    public boolean actualizarPedido(Pedido pedido) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == pedido.getId()) {
                pedidos.set(i, pedido);
                return true;
            }
        }
        return false;
    }

    public Pedido buscarPedido(int id) {
        return pedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public ObservableList<Pedido> obtenerTodos() {
        return pedidos;
    }
}
