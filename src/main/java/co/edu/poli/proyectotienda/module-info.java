module co.edu.poli.proyectotienda {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    
    opens co.edu.poli.proyectotienda to javafx.fxml, javafx.graphics;
    opens co.edu.poli.proyectotienda.controlador to javafx.fxml;
    opens co.edu.poli.proyectotienda.modelo to javafx.base;
    
    exports co.edu.poli.proyectotienda;
    exports co.edu.poli.proyectotienda.controlador;
    exports co.edu.poli.proyectotienda.modelo;
    exports co.edu.poli.proyectotienda.service;
    exports co.edu.poli.proyectotienda.strategy;
    exports co.edu.poli.proyectotienda.command;
    exports co.edu.poli.proyectotienda.chainofresponsibility;
    exports co.edu.poli.proyectotienda.ui;
}
