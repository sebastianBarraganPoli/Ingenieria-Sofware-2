module co.edu.poli.proyectotienda {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.base;

    opens co.edu.poli.proyectotienda.vista to javafx.fxml;
    opens co.edu.poli.proyectotienda.controlador to javafx.fxml;
    opens co.edu.poli.proyectotienda.modelo to javafx.base;
    
    exports co.edu.poli.proyectotienda.vista;
    exports co.edu.poli.proyectotienda.controlador;
}
