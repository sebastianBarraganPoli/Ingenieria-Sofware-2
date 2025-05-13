package co.edu.poli.proyectotienda.controlador;

import co.edu.poli.proyectotienda.service.SistemaPedidos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Tab tabPedidos;
    
    @FXML
    private Tab tabProductos;
    
    @FXML
    private Tab tabClientes;
    
    private SistemaPedidos sistema;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sistema = new SistemaPedidos();
        
        try {
            // Cargar las vistas para cada pestaña
            FXMLLoader pedidosLoader = new FXMLLoader(getClass().getResource("/fxml/PedidosView.fxml"));
            AnchorPane pedidosView = pedidosLoader.load();
            PedidosController pedidosController = pedidosLoader.getController();
            pedidosController.setSistema(sistema);
            tabPedidos.setContent(pedidosView);
            
            FXMLLoader productosLoader = new FXMLLoader(getClass().getResource("/fxml/ProductosView.fxml"));
            AnchorPane productosView = productosLoader.load();
            ProductosController productosController = productosLoader.getController();
            productosController.setSistema(sistema);
            tabProductos.setContent(productosView);
            
            FXMLLoader clientesLoader = new FXMLLoader(getClass().getResource("/fxml/ClientesView.fxml"));
            AnchorPane clientesView = clientesLoader.load();
            ClientesController clientesController = clientesLoader.getController();
            clientesController.setSistema(sistema);
            tabClientes.setContent(clientesView);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
