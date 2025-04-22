package co.edu.poli.proyectotienda.controlador;

import co.edu.poli.proyectotienda.modelo.ClienteFacade;
import co.edu.poli.proyectotienda.modelo.GestionCliente;
import co.edu.poli.proyectotienda.modelo.GestionFormasPago;
import co.edu.poli.proyectotienda.modelo.GestionPedidos;
import co.edu.poli.proyectotienda.modelo.Producto;
import co.edu.poli.proyectotienda.modelo.ProductoConProveedor;
import co.edu.poli.proyectotienda.modelo.ProductoProxy;
import co.edu.poli.proyectotienda.modelo.Proveedor;
import co.edu.poli.proyectotienda.modelo.ProveedorFactory;
import co.edu.poli.proyectotienda.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class Formulario {

    @FXML
    private Button btnFacade;
    
    @FXML
    private Button btnProxy;

    @FXML
    private Button btnFlyweight;

    @FXML
    private Label lblResultado;

    @FXML
    private Label lblProxyResultado;

    @FXML
    private Label lblFlyweightResultado;
    @FXML
    public void mostrarGestionCliente() {
        GestionCliente gestionCliente = new GestionCliente();
        GestionPedidos gestionPedidos = new GestionPedidos();
        GestionFormasPago gestionFormasPago = new GestionFormasPago();

        ClienteFacade facade = new ClienteFacade(gestionCliente, gestionPedidos, gestionFormasPago);


        String resultado = facade.mostrarInfoCliente() + "\n"
                        + facade.actualizarInfoCliente("Nombre: Laura Méndez, Dirección: Medellín") + "\n"
                        + facade.mostrarHistorialPedidos() + "\n"
                        + facade.realizarPedido("Pedido #789") + "\n"
                        + facade.mostrarFormasPago() + "\n"
                        + facade.activarFormaPago("PSE") + "\n"
                        + facade.bloquearFormaPago("Tarjeta");

        lblResultado.setText(resultado);
    }

    @FXML
    public void verProductoProxy() {
        Usuario usuario = new Usuario("Juan", 2); // Nivel bajo, prueba cambiar a 2
        Producto producto = new ProductoProxy(usuario, "TV Samsung 50\"", 2499000);

        lblProxyResultado.setText(producto.mostrarDetalles());
    }

    @FXML
    void mostrarEjemploFlyweight() {
        Proveedor proveedor1 = ProveedorFactory.obtenerProveedor("Proveedor A");
        Proveedor proveedor2 = ProveedorFactory.obtenerProveedor("Proveedor A");
        Proveedor proveedor3 = ProveedorFactory.obtenerProveedor("Proveedor B");

        ProductoConProveedor producto1 = new ProductoConProveedor("TV Samsung", 1500.0, proveedor1);
        ProductoConProveedor producto2 = new ProductoConProveedor("Laptop HP", 3200.0, proveedor2);
        ProductoConProveedor producto3 = new ProductoConProveedor("Microondas LG", 750.0, proveedor3);

        StringBuilder sb = new StringBuilder();
        sb.append(producto1.mostrarDetalles()).append("\n");
        sb.append(producto2.mostrarDetalles()).append("\n");
        sb.append(producto3.mostrarDetalles()).append("\n");

        sb.append("\n¿Proveedor1 == Proveedor2? ").append(proveedor1 == proveedor2);
        sb.append("\n¿Proveedor1 == Proveedor3? ").append(proveedor1 == proveedor3);

        lblFlyweightResultado.setText(sb.toString());
    }
}
