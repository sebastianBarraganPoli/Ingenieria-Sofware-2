package co.edu.poli.proyectotienda.controlador;

import co.edu.poli.proyectotienda.modelo.ClienteFacade;
import co.edu.poli.proyectotienda.modelo.GestionCliente;
import co.edu.poli.proyectotienda.modelo.GestionFormasPago;
import co.edu.poli.proyectotienda.modelo.GestionPedidos;
import co.edu.poli.proyectotienda.modelo.Producto;
import co.edu.poli.proyectotienda.modelo.ProductoProxy;
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
    private Label lblResultado;

    @FXML
    private Label lblProxyResultado;
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
}
