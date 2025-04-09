package co.edu.poli.proyectotienda.controlador;

import co.edu.poli.proyectotienda.modelo.ClienteFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class Formulario {

    @FXML
    private Button btnFacade;
    
    @FXML
    private Label lblResultado;
    @FXML
    public void mostrarGestionCliente() {
    ClienteFacade facade = new ClienteFacade();

    String resultado = facade.mostrarInfoCliente() + "\n"
                     + facade.actualizarInfoCliente("Nombre: Laura Méndez, Dirección: Medellín") + "\n"
                     + facade.mostrarHistorialPedidos() + "\n"
                     + facade.realizarPedido("Pedido #789") + "\n"
                     + facade.mostrarFormasPago() + "\n"
                     + facade.activarFormaPago("PSE") + "\n"
                     + facade.bloquearFormaPago("Tarjeta");

    lblResultado.setText(resultado);
}
}
