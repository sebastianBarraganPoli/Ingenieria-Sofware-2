package co.edu.poli.proyectotienda.ui;

import co.edu.poli.proyectotienda.command.CancelarPedidoComando;
import co.edu.poli.proyectotienda.command.CrearPedidoComando;
import co.edu.poli.proyectotienda.modelo.Cliente;
import co.edu.poli.proyectotienda.modelo.Pedido;
import co.edu.poli.proyectotienda.modelo.Producto;
import co.edu.poli.proyectotienda.service.SistemaPedidos;
import co.edu.poli.proyectotienda.strategy.PagoPayPal;
import co.edu.poli.proyectotienda.strategy.PagoTarjeta;
import co.edu.poli.proyectotienda.strategy.PagoTransferencia;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SistemaPedidosUI {
    private SistemaPedidos sistema;
    private TableView<Pedido> tablaPedidos;
    private TableView<Producto> tablaProductos;
    private ComboBox<Cliente> comboClientes;
    private ComboBox<String> comboMetodoPago;
    private ListView<String> listaCarrito;
    private Pedido pedidoActual;
    private TextField txtNumeroTarjeta;
    private TextField txtEmail;
    private TextField txtCuentaBancaria;

    public SistemaPedidosUI() {
        this.sistema = new SistemaPedidos();
    }

    public void iniciar(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Gestión de Pedidos");

        // Crear el layout principal
        BorderPane borderPane = new BorderPane();
        
        // Crear las pestañas
        TabPane tabPane = new TabPane();
        Tab tabPedidos = new Tab("Pedidos");
        Tab tabProductos = new Tab("Productos");
        Tab tabClientes = new Tab("Clientes");
        
        tabPedidos.setClosable(false);
        tabProductos.setClosable(false);
        tabClientes.setClosable(false);
        
        // Configurar la pestaña de pedidos
        tabPedidos.setContent(crearPanelPedidos());
        
        // Configurar la pestaña de productos
        tabProductos.setContent(crearPanelProductos());
        
        // Configurar la pestaña de clientes
        tabClientes.setContent(crearPanelClientes());
        
        tabPane.getTabs().addAll(tabPedidos, tabProductos, tabClientes);
        
        borderPane.setCenter(tabPane);
        
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Pane crearPanelPedidos() {
        BorderPane panel = new BorderPane();
        
        // Panel izquierdo para crear pedidos
        VBox panelCreacion = new VBox(10);
        panelCreacion.setPadding(new Insets(10));
        panelCreacion.setPrefWidth(400);
        
        Label lblTitulo = new Label("Crear Nuevo Pedido");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        Label lblCliente = new Label("Cliente:");
        comboClientes = new ComboBox<>(FXCollections.observableArrayList(sistema.getClientes()));
        
        Label lblProductos = new Label("Productos disponibles:");
        tablaProductos = new TableView<>();
        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Producto, Double> colPrecio = new TableColumn<>("Precio");
        TableColumn<Producto, Integer> colStock = new TableColumn<>("Stock");
        
        colNombre.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        colPrecio.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPrecio()).asObject());
        colStock.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        
        tablaProductos.getColumns().addAll(colNombre, colPrecio, colStock);
        tablaProductos.setItems(FXCollections.observableArrayList(sistema.getProductos()));
        tablaProductos.setPrefHeight(150);
        
        HBox panelCantidad = new HBox(10);
        Label lblCantidad = new Label("Cantidad:");
        Spinner<Integer> spinnerCantidad = new Spinner<>(1, 100, 1);
        spinnerCantidad.setEditable(true);
        Button btnAgregar = new Button("Agregar al Carrito");
        panelCantidad.getChildren().addAll(lblCantidad, spinnerCantidad, btnAgregar);
        
        Label lblCarrito = new Label("Carrito de compras:");
        listaCarrito = new ListView<>();
        listaCarrito.setPrefHeight(100);
        
        Label lblMetodoPago = new Label("Método de pago:");
        comboMetodoPago = new ComboBox<>(FXCollections.observableArrayList(
                "Tarjeta de Crédito", "PayPal", "Transferencia Bancaria"));
        
        VBox panelMetodoPago = new VBox(10);
        panelMetodoPago.setPadding(new Insets(10));
        
        txtNumeroTarjeta = new TextField();
        txtNumeroTarjeta.setPromptText("Número de tarjeta");
        txtNumeroTarjeta.setVisible(false);
        
        txtEmail = new TextField();
        txtEmail.setPromptText("Email de PayPal");
        txtEmail.setVisible(false);
        
        txtCuentaBancaria = new TextField();
        txtCuentaBancaria.setPromptText("Cuenta bancaria");
        txtCuentaBancaria.setVisible(false);
        
        panelMetodoPago.getChildren().addAll(txtNumeroTarjeta, txtEmail, txtCuentaBancaria);
        
        comboMetodoPago.setOnAction(e -> {
            String metodoPago = comboMetodoPago.getValue();
            txtNumeroTarjeta.setVisible(false);
            txtEmail.setVisible(false);
            txtCuentaBancaria.setVisible(false);
            
            if (metodoPago != null) {
                switch (metodoPago) {
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
        
        Button btnCrearPedido = new Button("Crear Pedido");
        Button btnProcesarPedido = new Button("Procesar Pedido");
        Button btnCancelarPedido = new Button("Cancelar Pedido");
        Button btnDeshacer = new Button("Deshacer");
        
        HBox panelBotones = new HBox(10);
        panelBotones.getChildren().addAll(btnCrearPedido, btnProcesarPedido, btnCancelarPedido, btnDeshacer);
        
        panelCreacion.getChildren().addAll(
                lblTitulo, lblCliente, comboClientes, 
                lblProductos, tablaProductos, panelCantidad, 
                lblCarrito, listaCarrito, 
                lblMetodoPago, comboMetodoPago, panelMetodoPago,
                panelBotones);
        
        // Panel derecho para mostrar pedidos
        VBox panelListado = new VBox(10);
        panelListado.setPadding(new Insets(10));
        
        Label lblPedidos = new Label("Listado de Pedidos");
        lblPedidos.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        tablaPedidos = new TableView<>();
        TableColumn<Pedido, Integer> colId = new TableColumn<>("ID");
        TableColumn<Pedido, String> colClientePedido = new TableColumn<>("Cliente");
        TableColumn<Pedido, String> colEstado = new TableColumn<>("Estado");
        
        colId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colClientePedido.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCliente().getNombre()));
        colEstado.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEstado().toString()));
        
        tablaPedidos.getColumns().addAll(colId, colClientePedido, colEstado);
        tablaPedidos.setPrefHeight(400);
        
        panelListado.getChildren().addAll(lblPedidos, tablaPedidos);
        
        // Configurar acciones de botones
        btnAgregar.setOnAction(e -> {
            Producto productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();
            if (productoSeleccionado != null) {
                int cantidad = spinnerCantidad.getValue();
                listaCarrito.getItems().add(productoSeleccionado.getNombre() + " x " + cantidad + 
                                           " = $" + (productoSeleccionado.getPrecio() * cantidad));
            } else {
                mostrarAlerta("Error", "Debe seleccionar un producto");
            }
        });
        
        btnCrearPedido.setOnAction(e -> {
            Cliente clienteSeleccionado = comboClientes.getValue();
            if (clienteSeleccionado == null) {
                mostrarAlerta("Error", "Debe seleccionar un cliente");
                return;
            }
            
            if (listaCarrito.getItems().isEmpty()) {
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
            for (String item : listaCarrito.getItems()) {
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
                actualizarTablaPedidos();
                mostrarAlerta("Éxito", "Pedido creado correctamente");
                limpiarFormulario();
            } else {
                mostrarAlerta("Error", "No se pudo crear el pedido");
            }
        });
        
        btnProcesarPedido.setOnAction(e -> {
            Pedido pedidoSeleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
            if (pedidoSeleccionado != null) {
                if (sistema.procesarPedido(pedidoSeleccionado)) {
                    actualizarTablaPedidos();
                    mostrarAlerta("Éxito", "Pedido procesado correctamente");
                } else {
                    mostrarAlerta("Error", "No se pudo procesar el pedido");
                }
            } else {
                mostrarAlerta("Error", "Debe seleccionar un pedido");
            }
        });
        
        btnCancelarPedido.setOnAction(e -> {
            Pedido pedidoSeleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
            if (pedidoSeleccionado != null) {
                CancelarPedidoComando comando = new CancelarPedidoComando(pedidoSeleccionado.getId(), sistema.getGestorPedidos());
                if (sistema.ejecutarComando(comando)) {
                    actualizarTablaPedidos();
                    mostrarAlerta("Éxito", "Pedido cancelado correctamente");
                } else {
                    mostrarAlerta("Error", "No se pudo cancelar el pedido");
                }
            } else {
                mostrarAlerta("Error", "Debe seleccionar un pedido");
            }
        });
        
        btnDeshacer.setOnAction(e -> {
            if (sistema.deshacerUltimoComando()) {
                actualizarTablaPedidos();
                mostrarAlerta("Éxito", "Operación deshecha correctamente");
            } else {
                mostrarAlerta("Error", "No hay operaciones para deshacer");
            }
        });
        
        // Actualizar tabla de pedidos
        actualizarTablaPedidos();
        
        // Configurar el panel principal
        panel.setLeft(panelCreacion);
        panel.setCenter(panelListado);
        
        return panel;
    }
    
    private Pane crearPanelProductos() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));
        
        Label lblTitulo = new Label("Gestión de Productos");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        TableView<Producto> tabla = new TableView<>();
        TableColumn<Producto, Integer> colId = new TableColumn<>("ID");
        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Producto, Double> colPrecio = new TableColumn<>("Precio");
        TableColumn<Producto, Integer> colStock = new TableColumn<>("Stock");
        
        colId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colNombre.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        colPrecio.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPrecio()).asObject());
        colStock.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        
        tabla.getColumns().addAll(colId, colNombre, colPrecio, colStock);
        tabla.setItems(FXCollections.observableArrayList(sistema.getProductos()));
        
        // Formulario para agregar productos
        GridPane formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(10);
        formulario.setPadding(new Insets(10));
        
        Label lblId = new Label("ID:");
        TextField txtId = new TextField();
        txtId.setPromptText("ID del producto");
        
        Label lblNombre = new Label("Nombre:");
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del producto");
        
        Label lblPrecio = new Label("Precio:");
        TextField txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio del producto");
        
        Label lblStock = new Label("Stock:");
        TextField txtStock = new TextField();
        txtStock.setPromptText("Stock disponible");
        
        Button btnAgregar = new Button("Agregar Producto");
        
        formulario.add(lblId, 0, 0);
        formulario.add(txtId, 1, 0);
        formulario.add(lblNombre, 0, 1);
        formulario.add(txtNombre, 1, 1);
        formulario.add(lblPrecio, 0, 2);
        formulario.add(txtPrecio, 1, 2);
        formulario.add(lblStock, 0, 3);
        formulario.add(txtStock, 1, 3);
        formulario.add(btnAgregar, 1, 4);
        
        btnAgregar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                int stock = Integer.parseInt(txtStock.getText());
                
                if (nombre.isEmpty()) {
                    mostrarAlerta("Error", "El nombre no puede estar vacío");
                    return;
                }
                
                Producto nuevoProducto = new Producto(id, nombre, precio, stock);
                sistema.agregarProducto(nuevoProducto);
                
                tabla.setItems(FXCollections.observableArrayList(sistema.getProductos()));
                tablaProductos.setItems(FXCollections.observableArrayList(sistema.getProductos()));
                
                txtId.clear();
                txtNombre.clear();
                txtPrecio.clear();
                txtStock.clear();
                
                mostrarAlerta("Éxito", "Producto agregado correctamente");
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "Los campos numéricos deben contener valores válidos");
            }
        });
        
        panel.getChildren().addAll(lblTitulo, tabla, formulario);
        
        return panel;
    }
    
    private Pane crearPanelClientes() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));
        
        Label lblTitulo = new Label("Gestión de Clientes");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        TableView<Cliente> tabla = new TableView<>();
        TableColumn<Cliente, Integer> colId = new TableColumn<>("ID");
        TableColumn<Cliente, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Cliente, String> colEmail = new TableColumn<>("Email");
        TableColumn<Cliente, String> colDireccion = new TableColumn<>("Dirección");
        
        colId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colNombre.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        colEmail.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));
        colDireccion.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDireccion()));
        
        tabla.getColumns().addAll(colId, colNombre, colEmail, colDireccion);
        tabla.setItems(FXCollections.observableArrayList(sistema.getClientes()));
        
        // Formulario para agregar clientes
        GridPane formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(10);
        formulario.setPadding(new Insets(10));
        
        Label lblId = new Label("ID:");
        TextField txtId = new TextField();
        txtId.setPromptText("ID del cliente");
        
        Label lblNombre = new Label("Nombre:");
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del cliente");
        
        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Email del cliente");
        
        Label lblDireccion = new Label("Dirección:");
        TextField txtDireccion = new TextField();
        txtDireccion.setPromptText("Dirección del cliente");
        
        Button btnAgregar = new Button("Agregar Cliente");
        
        formulario.add(lblId, 0, 0);
        formulario.add(txtId, 1, 0);
        formulario.add(lblNombre, 0, 1);
        formulario.add(txtNombre, 1, 1);
        formulario.add(lblEmail, 0, 2);
        formulario.add(txtEmail, 1, 2);
        formulario.add(lblDireccion, 0, 3);
        formulario.add(txtDireccion, 1, 3);
        formulario.add(btnAgregar, 1, 4);
        
        btnAgregar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                String email = txtEmail.getText();
                String direccion = txtDireccion.getText();
                
                if (nombre.isEmpty() || email.isEmpty() || direccion.isEmpty()) {
                    mostrarAlerta("Error", "Todos los campos son obligatorios");
                    return;
                }
                
                Cliente nuevoCliente = new Cliente(id, nombre, email, direccion);
                sistema.agregarCliente(nuevoCliente);
                
                tabla.setItems(FXCollections.observableArrayList(sistema.getClientes()));
                comboClientes.setItems(FXCollections.observableArrayList(sistema.getClientes()));
                
                txtId.clear();
                txtNombre.clear();
                txtEmail.clear();
                txtDireccion.clear();
                
                mostrarAlerta("Éxito", "Cliente agregado correctamente");
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "El ID debe ser un número válido");
            }
        });
        
        panel.getChildren().addAll(lblTitulo, tabla, formulario);
        
        return panel;
    }
    
    private void actualizarTablaPedidos() {
        tablaPedidos.setItems(FXCollections.observableArrayList(sistema.getGestorPedidos().obtenerTodos()));
    }
    
    private void limpiarFormulario() {
        comboClientes.setValue(null);
        listaCarrito.getItems().clear();
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
