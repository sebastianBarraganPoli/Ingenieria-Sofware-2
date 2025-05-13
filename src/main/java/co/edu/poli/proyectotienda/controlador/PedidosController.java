package co.edu.poli.proyectotienda.controlador;

import co.edu.poli.proyectotienda.command.CancelarPedidoComando;
import co.edu.poli.proyectotienda.command.CrearPedidoComando;
import co.edu.poli.proyectotienda.modelo.Cliente;
import co.edu.poli.proyectotienda.modelo.ItemPedido;
import co.edu.poli.proyectotienda.modelo.Pedido;
import co.edu.poli.proyectotienda.modelo.Producto;
import co.edu.poli.proyectotienda.service.SistemaPedidos;
import co.edu.poli.proyectotienda.strategy.PagoPayPal;
import co.edu.poli.proyectotienda.strategy.PagoTarjeta;
import co.edu.poli.proyectotienda.strategy.PagoTransferencia;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PedidosController implements Initializable {

    @FXML
    private ComboBox<Cliente> comboClientes;
    
    @FXML
    private TableView<Producto> tablaProductos;
    
    @FXML
    private TableColumn<Producto, String> colNombreProducto;
    
    @FXML
    private TableColumn<Producto, Double> colPrecioProducto;
    
    @FXML
    private TableColumn<Producto, Integer> colStockProducto;
    
    @FXML
    private Spinner<Integer> spinnerCantidad;
    
    @FXML
    private ListView<String> listaCarrito;
    
    @FXML
    private ComboBox<String> comboMetodoPago;
    
    @FXML
    private TextField txtNumeroTarjeta;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtCuentaBancaria;
    
    @FXML
    private TableView<Pedido> tablaPedidos;
    
    @FXML
    private TableColumn<Pedido, Integer> colIdPedido;
    
    @FXML
    private TableColumn<Pedido, String> colClientePedido;
    
    @FXML
    private TableColumn<Pedido, String> colEstadoPedido;
    
    @FXML
    private TableColumn<Pedido, String> colMetodoPagoPedido;
    
    @FXML
    private Button btnAgregar;
    
    @FXML
    private Button btnCrearPedido;
    
    @FXML
    private Button btnProcesarPedido;
    
    @FXML
    private Button btnCancelarPedido;
    
    @FXML
    private Button btnDeshacer;
    
    private SistemaPedidos sistema;
    private ObservableList<String> itemsCarrito = FXCollections.observableArrayList();
    private Pedido pedidoActual;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar spinner de cantidad
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        spinnerCantidad.setValueFactory(valueFactory);
        
        // Configurar combo de métodos de pago
        comboMetodoPago.setItems(FXCollections.observableArrayList(
                "Tarjeta de Crédito", "PayPal", "Transferencia Bancaria"));
        
        // Configurar lista de carrito
        listaCarrito.setItems(itemsCarrito);
        
        // Configurar visibilidad de campos de pago
        comboMetodoPago.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            txtNumeroTarjeta.setVisible(false);
            txtEmail.setVisible(false);
            txtCuentaBancaria.setVisible(false);
            
            if (newVal != null) {
                switch (newVal) {
                    case "Tarjeta de Crédito":
                        txtNumeroTarjeta.setVisible(true);
                        break;
                    case "PayPal":
                        txtEmail.setVisible(true);
                        break;
                    case "Transferencia Bancaria":
                        txtCuentaBancaria.setVisible(true);
                        break;
                }
            }
        });
        
        // Configurar acciones de botones
        btnAgregar.setOnAction(e -> agregarAlCarrito());
        btnCrearPedido.setOnAction(e -> crearPedido());
        btnProcesarPedido.setOnAction(e -> procesarPedido());
        btnCancelarPedido.setOnAction(e -> cancelarPedido());
        btnDeshacer.setOnAction(e -> deshacer());
    }
    
    public void setSistema(SistemaPedidos sistema) {
        this.sistema = sistema;
        
        // Configurar combo de clientes
        comboClientes.setItems(sistema.getClientes());
        
        // Configurar tabla de productos
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStockProducto.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tablaProductos.setItems(sistema.getProductos());
        
        // Configurar tabla de pedidos
        colIdPedido.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClientePedido.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getCliente().getNombre()));
        colEstadoPedido.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getEstado().toString()));
        colMetodoPagoPedido.setCellValueFactory(new PropertyValueFactory<>("metodoPagoNombre"));
        tablaPedidos.setItems(sistema.getGestorPedidos().obtenerTodos());
    }
    
    private void agregarAlCarrito() {
        Producto productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            int cantidad = spinnerCantidad.getValue();
            String item = productoSeleccionado.getNombre() + " x " + cantidad + 
                         " = $" + (productoSeleccionado.getPrecio() * cantidad);
            itemsCarrito.add(item);
        } else {
            mostrarAlerta("Error", "Debe seleccionar un producto");
        }
    }
    
    private void crearPedido() {
        Cliente clienteSeleccionado = comboClientes.getValue();
        if (clienteSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un cliente");
            return;
        }
        
        if (itemsCarrito.isEmpty()) {
            mostrarAlerta("Error", "El carrito está vacío");
            return;
        }
        
        String metodoPago = comboMetodoPago.getValue();
        if (metodoPago == null) {
            mostrarAlerta("Error", "Debe seleccionar un método de pago");
            return;
        }
        
        // Crear el pedido
        pedidoActual = new Pedido(sistema.getGestorPedidos().getNextId(), clienteSeleccionado);
        
        // Agregar productos al pedido
        for (String item : itemsCarrito) {
            String nombreProducto = item.split(" x ")[0];
            int cantidad = Integer.parseInt(item.split(" x ")[1].split(" =")[0]);
            
            Producto producto = sistema.getProductos().stream()
                    .filter(p -> p.getNombre().equals(nombreProducto))
                    .findFirst()
                    .orElse(null);
            
            if (producto != null) {
                pedidoActual.agregarItem(producto, cantidad);
            }
        }
        
        // Configurar estrategia de pago
        switch (metodoPago) {
            case "Tarjeta de Crédito":
                if (txtNumeroTarjeta.getText().isEmpty()) {
                    mostrarAlerta("Error", "Debe ingresar el número de tarjeta");
                    return;
                }
                pedidoActual.setEstrategiaPago(new PagoTarjeta(txtNumeroTarjeta.getText(), "12/25"));
                break;
            case "PayPal":
                if (txtEmail.getText().isEmpty()) {
                    mostrarAlerta("Error", "Debe ingresar el email de PayPal");
                    return;
                }
                pedidoActual.setEstrategiaPago(new PagoPayPal(txtEmail.getText()));
                break;
            case "Transferencia Bancaria":
                if (txtCuentaBancaria.getText().isEmpty()) {
                    mostrarAlerta("Error", "Debe ingresar la cuenta bancaria");
                    return;
                }
                pedidoActual.setEstrategiaPago(new PagoTransferencia(txtCuentaBancaria.getText()));
                break;
        }
        
        // Ejecutar comando para crear pedido
        CrearPedidoComando comando = new CrearPedidoComando(pedidoActual, sistema.getGestorPedidos());
        if (sistema.ejecutarComando(comando)) {
            mostrarAlerta("Éxito", "Pedido creado correctamente");
            limpiarFormulario();
        } else {
            mostrarAlerta("Error", "No se pudo crear el pedido");
        }
    }
    
    private void procesarPedido() {
        Pedido pedidoSeleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            if (sistema.procesarPedido(pedidoSeleccionado)) {
                mostrarAlerta("Éxito", "Pedido procesado correctamente");
            } else {
                mostrarAlerta("Error", "No se pudo procesar el pedido");
            }
        } else {
            mostrarAlerta("Error", "Debe seleccionar un pedido");
        }
    }
    
    private void cancelarPedido() {
        Pedido pedidoSeleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            CancelarPedidoComando comando = new CancelarPedidoComando(pedidoSeleccionado.getId(), sistema.getGestorPedidos());
            if (sistema.ejecutarComando(comando)) {
                mostrarAlerta("Éxito", "Pedido cancelado correctamente");
            } else {
                mostrarAlerta("Error", "No se pudo cancelar el pedido");
            }
        } else {
            mostrarAlerta("Error", "Debe seleccionar un pedido");
        }
    }
    
    private void deshacer() {
        if (sistema.deshacerUltimoComando()) {
            mostrarAlerta("Éxito", "Operación deshecha correctamente");
        } else {
            mostrarAlerta("Error", "No hay operaciones para deshacer");
        }
    }
    
    private void limpiarFormulario() {
        comboClientes.setValue(null);
        itemsCarrito.clear();
        comboMetodoPago.setValue(null);
        txtNumeroTarjeta.clear();
        txtEmail.clear();
        txtCuentaBancaria.clear();
        txtNumeroTarjeta.setVisible(false);
        txtEmail.setVisible(false);
        txtCuentaBancaria.setVisible(false);
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
