package co.edu.poli.proyectotienda.service;

import co.edu.poli.proyectotienda.chainofresponsibility.*;
import co.edu.poli.proyectotienda.command.Comando;
import co.edu.poli.proyectotienda.modelo.Cliente;
import co.edu.poli.proyectotienda.modelo.Pedido;
import co.edu.poli.proyectotienda.modelo.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Stack;

public class SistemaPedidos {
    private GestorPedidos gestorPedidos;
    private Stack<Comando> historialComandos;
    private ObservableList<Producto> productos;
    private ObservableList<Cliente> clientes;
    private ProcesadorPedido cadenaProcesamiento;

    public SistemaPedidos() {
        this.gestorPedidos = new GestorPedidos();
        this.historialComandos = new Stack<>();
        this.productos = FXCollections.observableArrayList();
        this.clientes = FXCollections.observableArrayList();
        
        // Configurar la cadena de responsabilidad
        ProcesadorPedido validador = new ValidadorPedido();
        ProcesadorPedido calculadorImpuestos = new CalculadorImpuestos();
        ProcesadorPedido verificador = new VerificadorInventario();
        ProcesadorPedido procesadorPago = new ProcesadorPago();
        ProcesadorPedido notificador = new NotificadorPedido();
        
        validador.setSiguiente(calculadorImpuestos);
        calculadorImpuestos.setSiguiente(verificador);
        verificador.setSiguiente(procesadorPago);
        procesadorPago.setSiguiente(notificador);
        
        this.cadenaProcesamiento = validador;
        
        // Inicializar con datos de ejemplo
        inicializarDatosEjemplo();
    }

    private void inicializarDatosEjemplo() {
        // Productos de ejemplo
        productos.add(new Producto(1, "Laptop", 1200.0, 10));
        productos.add(new Producto(2, "Smartphone", 800.0, 15));
        productos.add(new Producto(3, "Tablet", 500.0, 20));
        productos.add(new Producto(4, "Monitor", 300.0, 8));
        productos.add(new Producto(5, "Teclado", 50.0, 30));
        
        // Clientes de ejemplo
        clientes.add(new Cliente(1, "Juan Pérez", "juan@example.com", "Calle 123"));
        clientes.add(new Cliente(2, "María López", "maria@example.com", "Avenida 456"));
        clientes.add(new Cliente(3, "Carlos Gómez", "carlos@example.com", "Plaza 789"));
    }

    public boolean ejecutarComando(Comando comando) {
        if (comando.ejecutar()) {
            historialComandos.push(comando);
            return true;
        }
        return false;
    }

    public boolean deshacerUltimoComando() {
        if (!historialComandos.isEmpty()) {
            Comando comando = historialComandos.pop();
            return comando.deshacer();
        }
        return false;
    }

    public boolean procesarPedido(Pedido pedido) {
        return cadenaProcesamiento.procesarPedido(pedido);
    }

    public GestorPedidos getGestorPedidos() {
        return gestorPedidos;
    }

    public ObservableList<Producto> getProductos() {
        return productos;
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
}

