package co.edu.poli.proyectotienda.controlador;

import java.util.Arrays;

import co.edu.poli.proyectotienda.modelo.PriceSubject;
import co.edu.poli.proyectotienda.modelo.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Formulario {

    @FXML private TextField txtPorcentaje;
    @FXML private Button btnAumentar;
    @FXML private Label lblResultado;

    private PriceSubject subject;
    private Producto p1, p2, p3;

    @FXML
    public void initialize() {
        // Crear sujeto y productos
        subject = new PriceSubject();
        p1 = new Producto("Laptop", 1500.00);
        p2 = new Producto("Celular",  800.00);
        p3 = new Producto("Tablet",   400.00);

        // Registrar productos como observadores
        Arrays.asList(p1, p2, p3).forEach(subject::register);
    }

    @FXML
    public void aumentarPrecios() {
        try {
            double pct = Double.parseDouble(txtPorcentaje.getText());
            // Notificar a todos los productos
            subject.notifyAllObservers(pct);

            // Mostrar detalles actualizados
            String resultado = String.join("\n",
                p1.getDetalles(),
                p2.getDetalles(),
                p3.getDetalles()
            );
            lblResultado.setText(resultado);

        } catch (NumberFormatException e) {
            lblResultado.setText("🔴 Porcentaje inválido");
        }
    }
}
