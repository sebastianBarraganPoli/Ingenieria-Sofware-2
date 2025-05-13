package co.edu.poli.proyectotienda.modelo;

import co.edu.poli.proyectotienda.strategy.EstrategiaPago;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<ItemPedido> items;
    private Date fecha;
    private EstadoPedido estado;
    private EstrategiaPago estrategiaPago;

    public enum EstadoPedido {
        CREADO, VALIDADO, PAGADO, ENVIADO, ENTREGADO, CANCELADO
    }

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.items = new ArrayList<>();
        this.fecha = new Date();
        this.estado = EstadoPedido.CREADO;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public Date getFecha() {
        return fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public EstrategiaPago getEstrategiaPago() {
        return estrategiaPago;
    }

    public void setEstrategiaPago(EstrategiaPago estrategiaPago) {
        this.estrategiaPago = estrategiaPago;
    }

    public void agregarItem(Producto producto, int cantidad) {
        items.add(new ItemPedido(producto, cantidad));
    }

    public double calcularTotal() {
        return items.stream().mapToDouble(ItemPedido::calcularSubtotal).sum();
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " - Cliente: " + cliente.getNombre() + " - Estado: " + estado;
    }
}
