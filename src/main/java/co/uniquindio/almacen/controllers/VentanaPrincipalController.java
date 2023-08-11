package co.uniquindio.almacen.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

public class VentanaPrincipalController {

    private Stage stage;

    @FXML
    private Menu clientesBtn;

    @FXML
    private Menu productosBtn;

    @FXML
    private Menu transaccionesBtn;

    @FXML
    void abrirMenuClientes(ActionEvent event) {

    }

    @FXML
    void abrirMenuProductos(ActionEvent event) {

    }

    @FXML
    void abrirMenuTransacciones(ActionEvent event) {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.show();
    }

}

