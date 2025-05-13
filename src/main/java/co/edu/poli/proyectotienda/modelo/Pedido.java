package co.edu.poli.proyectotienda.modelo;

import co.edu.poli.proyectotienda.strategy.EstrategiaPago;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class Pedido {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final ObjectProperty<Cliente> cliente = new SimpleObjectProperty<>();
    private final ObservableList<ItemPedido> items = FXCollections.observableArrayList();
    private final ObjectProperty<Date> fecha = new SimpleObjectProperty<>();
    private final ObjectProperty<EstadoPedido> estado = new SimpleObjectProperty<>();
    private final ObjectProperty<EstrategiaPago> estrategiaPago = new SimpleObjectProperty<>();
    private final StringProperty metodoPagoNombre = new SimpleStringProperty();
    private double impuestos = 0.0;

    public enum EstadoPedido {
        CREADO, VALIDADO, PAGADO, ENVIADO, ENTREGADO, CANCELADO
    }

    public Pedido(int id, Cliente cliente) {
        this.id.set(id);
        this.cliente.set(cliente);
        this.fecha.set(new Date());
        this.estado.set(EstadoPedido.CREADO);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public Cliente getCliente() {
        return cliente.get();
    }

    public ObjectProperty<Cliente> clienteProperty() {
        return cliente;
    }

    public ObservableList<ItemPedido> getItems() {
        return items;
    }

    public Date getFecha() {
        return fecha.get();
    }

    public ObjectProperty<Date> fechaProperty() {
        return fecha;
    }

    public EstadoPedido getEstado() {
        return estado.get();
    }

    public ObjectProperty<EstadoPedido> estadoProperty() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado.set(estado);
    }

    public EstrategiaPago getEstrategiaPago() {
        return estrategiaPago.get();
    }

    public ObjectProperty<EstrategiaPago> estrategiaPagoProperty() {
        return estrategiaPago;
    }

    public void setEstrategiaPago(EstrategiaPago estrategiaPago) {
        this.estrategiaPago.set(estrategiaPago);
        if (estrategiaPago != null) {
            this.metodoPagoNombre.set(estrategiaPago.getNombre());
        }
    }

    public String getMetodoPagoNombre() {
        return metodoPagoNombre.get();
    }

    public StringProperty metodoPagoNombreProperty() {
        return metodoPagoNombre;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public void agregarItem(Producto producto, int cantidad) {
        items.add(new ItemPedido(producto, cantidad));
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : items) {
            subtotal += item.calcularSubtotal();
        }
        return subtotal;
    }

    public double calcularTotal() {
        return calcularSubtotal() + impuestos;
    }

    @Override
    public String toString() {
        return "Pedido #" + getId() + " - Cliente: " + getCliente().getNombre() + " - Estado: " + getEstado();
    }
}
