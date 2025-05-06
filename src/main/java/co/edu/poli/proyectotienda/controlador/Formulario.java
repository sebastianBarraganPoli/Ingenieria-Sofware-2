package co.edu.poli.proyectotienda.controlador;

import co.edu.poli.proyectotienda.modelo.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.Year;
import java.util.Arrays;

public class Formulario {

    @FXML
    private TextField txtPorcentaje;
    @FXML
    private TextField txtAnio;

    @FXML
    private Button btnAumentar;
    @FXML
    private Button btnRestaurarPrecio;
    @FXML
    private Button btnBuscarAnio;

    @FXML
    private Label lblResultado;
    @FXML
    private Label lblPrecioMemeto;

    private PriceSubject subject;
    private Producto p1, p2, p3;
    private CaretakerProducto caretaker;

    @FXML
    public void initialize() {
        subject = new PriceSubject();
        caretaker = new CaretakerProducto();

        p1 = new Producto("Laptop", 1500.00);
        p2 = new Producto("Celular", 800.00);
        p3 = new Producto("Tablet", 400.00);

        // 1) Pre-cargar históricos manuales:
        // Para p1:
        double precioReal = p1.getPrecio();

        p1.setPrecio(1000.00);                      // Simula precio en 2020
        caretaker.guardarEstado(p1, 2020);
        p1.setPrecio(1200.00);                      // Simula precio en 2021
        caretaker.guardarEstado(p1, 2021);            

        // Repite para p2, p3 si quieres
        p2.setPrecio(600.00);
        caretaker.guardarEstado(p2, 2020);
        p2.setPrecio(700.00);
        caretaker.guardarEstado(p2, 2021);

        // Repite para p2, p3 si quieres
        p3.setPrecio(100.00);
        caretaker.guardarEstado(p3, 2020);
        p3.setPrecio(200.00);
        caretaker.guardarEstado(p3, 2021);

        // Registrar observadores
        Arrays.asList(p1, p2, p3).forEach(subject::register);
    }

    @FXML
    public void aumentarPrecios() {
        try {
            double pct = Double.parseDouble(txtPorcentaje.getText());
            int anio = Year.now().getValue(); // Usa el año actual

            // Guardar estado con año
            Arrays.asList(p1, p2, p3).forEach(p -> caretaker.guardarEstado(p, anio));

            subject.notifyAllObservers(pct);

            String resultado = String.join("\n",
                    String.format("🛒 %s: $%.2f", p1.getNombre(), p1.getPrecio()),
                    String.format("🛒 %s: $%.2f", p2.getNombre(), p2.getPrecio()),
                    String.format("🛒 %s: $%.2f", p3.getNombre(), p3.getPrecio()));
            lblResultado.setText(resultado);

        } catch (NumberFormatException e) {
            lblResultado.setText("🔴 Porcentaje inválido");
        }
    }

    @FXML
    private void restaurarPrecio() {
        // Restaurar cada producto
        Arrays.asList(p1, p2, p3).forEach(caretaker::restaurarEstado);

        // Mostrar precios restaurados
        String resultado = String.join("\n",
                String.format("🔄 %s: $%.2f", p1.getNombre(), p1.getPrecio()),
                String.format("🔄 %s: $%.2f", p2.getNombre(), p2.getPrecio()),
                String.format("🔄 %s: $%.2f", p3.getNombre(), p3.getPrecio()));
        lblResultado.setText(resultado);
    }

    @FXML
    private void buscarPrecioPorAnio() {
        try {
            int anio = Integer.parseInt(txtAnio.getText());

            StringBuilder resultado = new StringBuilder();
            for (Producto p : Arrays.asList(p1, p2, p3)) {
                MementoProducto m = caretaker.obtenerPrecioPorAnio(p, anio);
                if (m != null) {
                    resultado.append("📅 ")
                            .append(p.getNombre())
                            .append(" en ")
                            .append(anio)
                            .append(": $")
                            .append(m.getPrecio())
                            .append("\n");
                } else {
                    resultado.append("❌ ")
                            .append(p.getNombre())
                            .append(": No hay registro en ")
                            .append(anio)
                            .append("\n");
                }
            }

            lblPrecioMemeto.setText(resultado.toString());

        } catch (NumberFormatException e) {
            lblPrecioMemeto.setText("🔴 Año inválido");
        }
    }
}
