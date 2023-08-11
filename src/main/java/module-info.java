module com.example.proyectofx {

    requires javafx.controls;
    requires java.desktop;
    requires java.logging;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens co.uniquindio.almacen.application to javafx.graphics, javafx.fxml;
    opens co.uniquindio.almacen.model to javafx.base;
    opens co.uniquindio.almacen.controllers to javafx.fxml;

}