package co.uniquindio.almacen.controllers;

import co.uniquindio.almacen.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaPrincipalController {

    private Stage stage;

    @FXML
    private Menu clientesBtn;

    @FXML
    private Menu productosBtn;

    @FXML
    private Menu transaccionesBtn;

    @FXML
    void abrirMenuClientes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("RegistroClientes.fxml"));
        Parent root = loader.load();
        RegistroClientesController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }

    @FXML
    void abrirMenuProductos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("RegistroProductos.fxml"));
        Parent root = loader.load();
        RegistroProductosController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }

    @FXML
    void abrirMenuTransacciones(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("RegistroTransacciones.fxml"));
        Parent root = loader.load();
        RegistroTransaccionesController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void show() {
        stage.show();
    }
}


