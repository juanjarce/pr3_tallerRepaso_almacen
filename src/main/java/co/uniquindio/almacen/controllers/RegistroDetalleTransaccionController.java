package co.uniquindio.almacen.controllers;

import co.uniquindio.almacen.exceptions.ObjectException;
import co.uniquindio.almacen.model.Cliente;
import co.uniquindio.almacen.model.DetalleTransaccion;
import co.uniquindio.almacen.model.Producto;
import co.uniquindio.almacen.model.Transaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RegistroDetalleTransaccionController implements Initializable {

    private Stage stage;
    private ModelFactoryController mfm = ModelFactoryController.getInstance();
    private ObservableList<DetalleTransaccion> listaDetallesTransaccionData;
    private RegistroTransaccionesController registroTransaccionesController;
    private DetalleTransaccion detalleTransaccionSeleccionado;
    private Transaccion transaccion;

    @FXML
    private Label labelTransaccion;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnNuevoDetalle;

    @FXML
    private ComboBox comboProducto;

    @FXML
    private TextField inputCantidad;

    @FXML
    private TextField inputCodigoTransaccion;

    @FXML
    private Label labelSubtotal;

    @FXML
    private TextField inputSubtotal;

    @FXML
    private TableView<DetalleTransaccion> tableDetallesTransaccion;

    @FXML
    private TableColumn<DetalleTransaccion, Transaccion> columnTransaccion;

    @FXML
    private TableColumn<DetalleTransaccion, Producto> columnProducto;

    @FXML
    private TableColumn<DetalleTransaccion, Integer> columnCantidad;

    @FXML
    private TableColumn<DetalleTransaccion, Double> columnSubtotal;

    @FXML
    private Button btnVolver;

    @FXML
    void agregarDetalleTransaccionEvent(ActionEvent event) {
        String codigoProducto = comboProducto.getSelectionModel().getSelectedItem().toString();
        String cantidadProducto = inputCantidad.getText();

        if(validarDatos(codigoProducto, cantidadProducto)) {

            try {

                String mensaje = mfm.realizarDetalleTransaccion(this.transaccion.getCodigo(), codigoProducto, Integer.parseInt(cantidadProducto));
                mostrarMensaje("Detalle de Transaccion Realizado", "Tarea Completada:", mensaje, Alert.AlertType.INFORMATION);

                Producto producto = mfm.obtenerProducto(codigoProducto);
                double subtotal = Integer.parseInt(cantidadProducto) * producto.getValorUnitario();
                DetalleTransaccion dt = new DetalleTransaccion(this.transaccion, producto, Integer.parseInt(cantidadProducto), subtotal);

                if(!this.listaDetallesTransaccionData.contains(dt)) {
                    this.listaDetallesTransaccionData.add(dt);
                    this.tableDetallesTransaccion.setItems(listaDetallesTransaccionData);

                    comboProducto.getSelectionModel().clearSelection(); inputCantidad.setText(null);
                    ocultarDatos();
                }

            } catch (ObjectException e) {
                mostrarMensaje("Error:", "Registro Transaccion:", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    void nuevoDetalleTransaccionEvent(ActionEvent event) {
        comboProducto.getSelectionModel().clearSelection();
        inputCantidad.setText(null);
        ocultarDatos();
    }

    @FXML
    void seleccionarDetalleTransaccionEvent(MouseEvent event) {
        this.detalleTransaccionSeleccionado = this.tableDetallesTransaccion.getSelectionModel().getSelectedItem();

        if(this.detalleTransaccionSeleccionado!=null) {
            comboProducto.getSelectionModel().select(this.detalleTransaccionSeleccionado.getProducto().getCodigo());
            inputCantidad.setText(Integer.toString(this.detalleTransaccionSeleccionado.getCantidadProducto()));
            labelTransaccion.setVisible(true); inputCodigoTransaccion.setVisible(true); inputCodigoTransaccion.setText(this.transaccion.getCodigo());
            labelSubtotal.setVisible(true); inputSubtotal.setVisible(true); inputSubtotal.setText("$"+" "+this.detalleTransaccionSeleccionado.getSubtotal());
        }
    }

    @FXML
    void volverEvent(ActionEvent event) {
        registroTransaccionesController.show();
        stage.close();
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public void init(Stage stage, RegistroTransaccionesController registroTransaccionesController, Transaccion transaccion) {
        this.stage = stage;
        this.registroTransaccionesController = registroTransaccionesController;
        this.transaccion = transaccion;

        listaDetallesTransaccionData.addAll(transaccion.getListaDetalleTransaccion());
        tableDetallesTransaccion.setItems(listaDetallesTransaccionData);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaDetallesTransaccionData = FXCollections.observableArrayList();

        this.columnTransaccion.setCellValueFactory(new PropertyValueFactory<DetalleTransaccion, Transaccion>("transaccion"));
        this.columnProducto.setCellValueFactory(new PropertyValueFactory<DetalleTransaccion, Producto>("producto"));
        this.columnCantidad.setCellValueFactory(new PropertyValueFactory<DetalleTransaccion, Integer>("cantidadProducto"));
        this.columnSubtotal.setCellValueFactory(new PropertyValueFactory<DetalleTransaccion, Double>("subtotal"));

        ObservableList<String> listaProductos = FXCollections.observableArrayList();
        listaProductos.addAll(mfm.obtenerCodigosProductos());
        comboProducto.setItems(listaProductos);

        ocultarDatos();

    }

    private boolean validarDatos(String codigoProducto, String cantidadProducto) {
        String mensaje = "";

        if(codigoProducto == null || codigoProducto.isEmpty())
            mensaje += "El codigo del producto es invalido \n";

        if(cantidadProducto == null || cantidadProducto.isEmpty())
            mensaje += "La cantidad de producto es invalida \n";

        if(mensaje.isEmpty()){
            return true;
        }else{
            mostrarMensaje("Información Cliente", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    public void ocultarDatos(){
        labelTransaccion.setVisible(false); inputCodigoTransaccion.setVisible(false); inputCodigoTransaccion.setText(null);
        labelSubtotal.setVisible(false); inputSubtotal.setVisible(false); inputSubtotal.setText(null);
    }
}


