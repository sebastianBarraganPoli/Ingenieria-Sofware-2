package co.edu.poli.proyectotienda.controlador;

import co.edu.poli.proyectotienda.modelo.Cliente;
import co.edu.poli.proyectotienda.service.SistemaPedidos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tablaClientes;
    
    @FXML
    private TableColumn<Cliente, Integer> colId;
    
    @FXML
    private TableColumn<Cliente, String> colNombre;
    
    @FXML
    private TableColumn<Cliente, String> colEmail;
    
    @FXML
    private TableColumn<Cliente, String> colDireccion;
    
    @FXML
    private TextField txtId;
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtDireccion;
    
    @FXML
    private Button btnAgregar;
    
    private SistemaPedidos sistema;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAgregar.setOnAction(e -> agregarCliente());
    }
    
    public void setSistema(SistemaPedidos sistema) {
        this.sistema = sistema;
        
        // Configurar tabla de clientes
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tablaClientes.setItems(sistema.getClientes());
    }
    
    private void agregarCliente() {
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
            
            txtId.clear();
            txtNombre.clear();
            txtEmail.clear();
            txtDireccion.clear();
            
            mostrarAlerta("Éxito", "Cliente agregado correctamente");
        } catch (NumberFormatException ex) {
            mostrarAlerta("Error", "El ID debe ser un número válido");
        }
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
