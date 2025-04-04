package co.edu.poli.proyectotienda.controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import co.edu.poli.proyectotienda.modelo.CargaFragil;
import co.edu.poli.proyectotienda.modelo.Certificacion;
import co.edu.poli.proyectotienda.modelo.Cliente;
import co.edu.poli.proyectotienda.modelo.Carrito;
import co.edu.poli.proyectotienda.modelo.CarritoBase;
import co.edu.poli.proyectotienda.modelo.Departamento;
import co.edu.poli.proyectotienda.modelo.Descuento;
import co.edu.poli.proyectotienda.modelo.Empleado;
import co.edu.poli.proyectotienda.modelo.Envio;
import co.edu.poli.proyectotienda.modelo.EnvioGratis;
import co.edu.poli.proyectotienda.modelo.EnvioNacional;
import co.edu.poli.proyectotienda.modelo.Evaluacion;
import co.edu.poli.proyectotienda.modelo.Mercancia;
import co.edu.poli.proyectotienda.modelo.Pago;
import co.edu.poli.proyectotienda.modelo.PagoAdapterNequi;
import co.edu.poli.proyectotienda.modelo.PagoAdapterPaypal;
import co.edu.poli.proyectotienda.modelo.PagoNequi;
import co.edu.poli.proyectotienda.modelo.PagoPaypal;
import co.edu.poli.proyectotienda.modelo.PoliticaEntrega;
import co.edu.poli.proyectotienda.modelo.ProductoElectronico;
import co.edu.poli.proyectotienda.modelo.Proveedor;
import co.edu.poli.proyectotienda.modelo.Proveedor.ProveedorBuilder;
import co.edu.poli.proyectotienda.modelo.Puntos;
import co.edu.poli.proyectotienda.servicios.ClienteDAOImpl;
import co.edu.poli.proyectotienda.servicios.ConexionDB;
import co.edu.poli.proyectotienda.servicios.ProveedorDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Formulario {

    @FXML
    private Button btn_1;

    @FXML
    private Button btn_2;

    @FXML
    private Button btn_3;

    @FXML
    private Button btn_4;
    
    @FXML
    private Button btn_5;

    @FXML
    private Button btnCrearProveedor;

    @FXML
    private Button btn_envio;

    @FXML
    private Button btnComposite;

    @FXML
    private Button btn_decorator;

    @FXML
    private Label lblResultado;
    
    @FXML
    private TextArea txtAreaClonado;

    @FXML
    private TextField txt_1;

    @FXML
    private TextField txt_2;
    
    @FXML
    private TableColumn<Cliente, String> colId;

    @FXML
    private TableColumn<Cliente, String> colNombre;
    
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private Label lblEstructura;

    @FXML
    private Button btn_adapter;

    @FXML
    private Label lblPago;

    @FXML
    private Label lblCarrito;

    @FXML
    private Label lblEnvio;
    
    private ClienteDAOImpl clienteDAO;
    private ProveedorDAOImpl proveedorDAO;
    private Connection conexion;
    
    private ProductoElectronico productoOriginal = new ProductoElectronico("P001", "Laptop", 1500.0, 220);

    @FXML
    public void initialize() throws SQLException {
    	conexion = ConexionDB.getInstancia().getConexion(); // Asignar a la variable global
        clienteDAO = new ClienteDAOImpl(conexion);
        proveedorDAO = new ProveedorDAOImpl(conexion);
        System.out.println("Conexión exitosa con la base de datos.");
        
     // Configurar las columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        // Cargar los clientes en la tabla
        cargarClientes();
        
        // Asociar eventos a los botones
        btn_1.setOnAction(event -> crearCliente(event));
        btn_2.setOnAction(event -> editarCliente(event));
        btn_3.setOnAction(event -> eliminarCliente(event));
        btn_4.setOnAction(event -> buscarCliente(event));
    }
    
    
    @FXML
    void buscarCliente(ActionEvent event) {

    	String id = txt_1.getText();

        if (id.isEmpty()) {
            mostrarAlerta("Campo vacío", "Por favor, ingresa el ID.");
            return;
        }

        Object resultado = clienteDAO.read(id);
        if (resultado instanceof Cliente) {
            Cliente cliente = (Cliente) resultado;
            txt_2.setText(cliente.getNombre());
            mostrarAlerta("Cliente Encontrado", "ID: " + cliente.getId() + "\nNombre: " + cliente.getNombre());
        } else {
            mostrarAlerta("Buscar Cliente", "Cliente no encontrado.");
        }
    }

    @FXML
    void crearCliente(ActionEvent event) {

    	String id = txt_1.getText();
        String nombre = txt_2.getText();
        
        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, ingresa el ID y el Nombre.");
            return;
        }
        
        Cliente cliente = new Cliente(id, nombre);
        String mensaje = clienteDAO.create(cliente);
        mostrarAlerta("Crear Cliente", mensaje);
        
        limpiarCampos();
        cargarClientes();
    }

    @FXML
    void editarCliente(ActionEvent event) {

    	String id = txt_1.getText();
        String nombre = txt_2.getText();
        
        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, ingresa el ID y el Nombre.");
            return;
        }

        Cliente cliente = new Cliente(id, nombre);
        String mensaje = clienteDAO.update(id, cliente);
        mostrarAlerta("Editar Cliente", mensaje);
        
        limpiarCampos();
        cargarClientes();
    }

    @FXML
    void eliminarCliente(ActionEvent event) {

    	String id = txt_1.getText();

        if (id.isEmpty()) {
            mostrarAlerta("Campo vacío", "Por favor, ingresa el ID.");
            return;
        }

        String mensaje = clienteDAO.delete(id);
        mostrarAlerta("Eliminar Cliente", mensaje);
        
        limpiarCampos();
        cargarClientes();
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Limpiar campos de entrada
    private void limpiarCampos() {
        txt_1.clear();
        txt_2.clear();
    }
    
    @FXML
    private void clonarProducto() {
        // Clonar el producto
        ProductoElectronico productoClonado = (ProductoElectronico) productoOriginal.clonar();

        // Construir la información de ambos productos
        String infoOriginal = "🔵 **Producto Original:**\n" +
                              "ID: " + productoOriginal.getId() + "\n" +
                              "Descripción: " + productoOriginal.getDescripcion() + "\n" +
                              "Precio: $" + productoOriginal.getPrecio() + "\n" +
                              "Voltaje: " + productoOriginal.getVoltajeEntrada() + "V\n\n";

        String infoClonado = "🟢 **Producto Clonado:**\n" +
                             "ID: " + productoClonado.getId() + "\n" +
                             "Descripción: " + productoClonado.getDescripcion() + "\n" +
                             "Precio: $" + productoClonado.getPrecio() + "\n" +
                             "Voltaje: " + productoClonado.getVoltajeEntrada() + "V";

        // Mostrar en el TextArea
        txtAreaClonado.setText(infoOriginal + infoClonado);
        System.out.println("✅ Producto clonado correctamente.");
    }
    
    private void cargarClientes() {
        List<Object> clientesList = clienteDAO.list();

        // Convertir a ObservableList
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();

        for (Object obj : clientesList) {
            if (obj instanceof Cliente) {
                clientes.add((Cliente) obj);
            }
        }

        // Cargar datos en la tabla
        tablaClientes.setItems(clientes);
    }

    @FXML
    public void crearProveedor() {
        // Construcción del proveedor usando el Builder
        Proveedor proveedor = new ProveedorBuilder()
            .setNombre("Proveedor ABC")
            .setCertificacion(new Certificacion("ISO 25001"))
            .setEvaluacion(new Evaluacion(85))
            .setPoliticaEntrega(new PoliticaEntrega("Delayed"))
            .build();

        // Guardar el proveedor en la base de datos
        proveedorDAO.create(proveedor);

        String infoProveedor = String.format(
            "✅ Proveedor guardado con éxito:\n" +
            "📌 Nombre: %s\n" +
            "📜 Certificación: %s\n" +
            "⭐ Evaluación: %d\n" +
            "🚚 Política de Entrega: %s",
            proveedor.getNombre(),
            proveedor.getCertificacion().getTipo(),
            proveedor.getEvaluacion().getPuntaje(),
            proveedor.getPoliticaEntrega().getTipo()
        );

        // Mostrar en la UI
        lblResultado.setText(infoProveedor);
    }
    
    public void mostrarEstructuraEmpresa() {
        // Crear departamentos
        Departamento ventas = new Departamento("Ventas");
        Departamento tecnologia = new Departamento("Tecnología");

        // Crear empleados
        Empleado emp1 = new Empleado("Carlos Gómez");
        Empleado emp2 = new Empleado("Ana Martínez");
        Empleado emp3 = new Empleado("Luis Fernández");

        // Agregar empleados a departamentos
        ventas.agregar(emp1);
        ventas.agregar(emp2);
        tecnologia.agregar(emp3);

        // Crear un departamento general y agregar los otros departamentos
        Departamento empresa = new Departamento("Empresa XYZ");
        empresa.agregar(ventas);
        empresa.agregar(tecnologia);

        // Mostrar la estructura en el Label
        lblEstructura.setText(empresa.mostrarInfo());
    }

    @FXML
    private void realizarPago() {
        
        double monto = 100.0; // Ejemplo de monto a pagar
 
        // Pago con Nequi
        Pago pagoNequi = new PagoAdapterNequi(new PagoNequi());
        String resultadoNequi = pagoNequi.procesarPago(monto);
    
        // Pago con PayPal
        Pago pagoPaypal = new PagoAdapterPaypal(new PagoPaypal());
        String resultadoPaypal = pagoPaypal.procesarPago(monto);
    
        // Mostrar el resultado en el Label
        lblPago.setText(resultadoNequi + "\n" + resultadoPaypal);
    }
    
    @FXML
    private void realizarEnvio() {
        // Crear una mercancía
        Mercancia carga = new CargaFragil();  // Prueba con CargaPesada o Documentos

        // Crear un envío
        Envio envio = new EnvioNacional(carga);  // Prueba con EnvioInternacional

        // Mostrar en la UI
        lblEnvio.setText(envio.entregar());
    }

    @FXML
    private void aplicarDecoradores() {
        // Crear carrito y agregar productos
        Carrito carrito = new CarritoBase();
        ((CarritoBase) carrito).agregarItem(new ProductoElectronico("P001", "Laptop", 1500, 220));
        ((CarritoBase) carrito).agregarItem(new ProductoElectronico("P002", "Teclado", 50, 5));

        // Aplicar decoradores
        carrito = new Descuento(carrito, 10); // 10% de descuento
        carrito = new EnvioGratis(carrito); // Envío gratis
        carrito = new Puntos(carrito); // Acumulación de puntos

        // Mostrar resultado en la UI
        lblCarrito.setText(carrito.getDescripcion() + "\n💲 Total: $" + carrito.getTotal());
    }
}
