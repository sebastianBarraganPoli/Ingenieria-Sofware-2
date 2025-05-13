package co.edu.poli.proyectotienda;

import co.edu.poli.proyectotienda.ui.SistemaPedidosUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        SistemaPedidosUI ui = new SistemaPedidosUI();
        ui.iniciar(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
