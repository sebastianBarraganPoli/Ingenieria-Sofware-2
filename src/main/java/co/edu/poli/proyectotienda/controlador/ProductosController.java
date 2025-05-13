package co.edu.poli.proyectotienda.controlador;

import co.edu.poli.proyectotienda.modelo.Producto;
import co.edu.poli.proyectotienda.service.SistemaPedidos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductosController implements Initializable {

    @FXML
    private TableView<Producto> tablaProductos;
    
    @FXML
    private TableColumn<Producto, Integer> colId;
    
    @FXML
    private TableColumn<Producto, String> colNombre;
    
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    
    @FXML
    private TableColumn<Producto, Integer> colStock;
    
    @FXML
    private TextField txtId;
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtPrecio;
    
    @FXML
    private TextField txtStock;
    
    @FXML
    private Button btnAgregar;
    
    private SistemaPedidos sistema;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAgregar.setOnAction(e -> agregarProducto());
    }
    
    public void setSistema(SistemaPedidos sistema) {
        this.sistema = sistema;
        
        // Configurar tabla de productos
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tablaProductos.setItems(sistema.getProductos());
    }
    
    private void agregarProducto() {
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
            
            txtId.clear();
            txtNombre.clear();
            txtPrecio.clear();
            txtStock.clear();
            
            mostrarAlerta("Éxito", "Producto agregado correctamente");
        } catch (NumberFormatException ex) {
            mostrarAlerta("Error", "Los campos numéricos deben contener valores válidos");
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
