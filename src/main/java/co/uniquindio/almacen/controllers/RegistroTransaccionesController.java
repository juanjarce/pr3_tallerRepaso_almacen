package co.uniquindio.almacen.controllers;

import co.uniquindio.almacen.application.Application;
import co.uniquindio.almacen.exceptions.ObjectException;
import co.uniquindio.almacen.model.Cliente;
import co.uniquindio.almacen.model.TipoCliente;
import co.uniquindio.almacen.model.Transaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class RegistroTransaccionesController implements Initializable {

    private Stage stage;
    private ModelFactoryController mfm = ModelFactoryController.getInstance();
    private ObservableList<Transaccion> listaTransaccionData;
    private VentanaPrincipalController ventanaPrincipalController;
    private Transaccion transaccionSeleccionada;
    private String fechaTransaccion;

    @FXML
    private TextField inputCodigo;

    @FXML
    private Button btnAgregar;

    @FXML
    private DatePicker dateFecha;

    @FXML
    private ComboBox comboCliente;

    @FXML
    private Label labelTotal;

    @FXML
    private TextField inputTotal;

    @FXML
    private Label labelIVA;

    @FXML
    private TextField inputIVA;

    @FXML
    private TableView<Transaccion> tableTransacciones;

    @FXML
    private TableColumn<Transaccion, String> columnCodigo;

    @FXML
    private TableColumn<Transaccion, String> columnFecha;

    @FXML
    private TableColumn<Transaccion, Double> columnTotal;

    @FXML
    private TableColumn<Transaccion, Double> columnIva;

    @FXML
    private TableColumn<Transaccion, Cliente> columnCliente;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVolver;

    @FXML
    void agregarTransaccionEvent(ActionEvent event) {
        String codigo = inputCodigo.getText(); String identificacionCliente = comboCliente.getSelectionModel().getSelectedItem().toString();

        if(validarDatos(codigo, fechaTransaccion, identificacionCliente)) {

            try {
                String mensaje = mfm.realizarTransaccion(codigo, fechaTransaccion, identificacionCliente);
                mostrarMensaje("Transaccion Realizada", "Tarea Completada:", mensaje, Alert.AlertType.INFORMATION);

                Transaccion t = mfm.obtenerTransaccion(codigo);

                if(!this.listaTransaccionData.contains(t)) {
                    this.listaTransaccionData.add(t);
                    this.tableTransacciones.setItems(listaTransaccionData);

                    limpiarDatos();
                }

            } catch (ObjectException e) {
                mostrarMensaje("Error:", "Registro Transaccion:", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    void eliminarTransaccionEvent(ActionEvent event) {
        if(this.transaccionSeleccionada!=null) {
            this.listaTransaccionData.remove(this.transaccionSeleccionada);
            this.tableTransacciones.refresh();
            try {
                String mensaje = mfm.eliminarTransaccion(this.transaccionSeleccionada.getCodigo());
                mostrarMensaje("Transaccion Eliminada", "Tarea Completada:", mensaje, Alert.AlertType.INFORMATION);

                limpiarDatos();
                this.transaccionSeleccionada = null;
            } catch (ObjectException e) {
                mostrarMensaje("Error:", "Eliminación Transaccion:", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje("Información Transaccion", "Transaccion No Seleccionada", "No se ha seleccionado ninguna transaccion", Alert.AlertType.WARNING);
        }

    }

    @FXML
    void gestionarDetalleTransaccion(ActionEvent event) throws IOException {
        if(this.transaccionSeleccionada!=null){
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("RegistroDetalleTransaccion.fxml"));
            Parent root = loader.load();
            RegistroDetalleTransaccionController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            controller.init(stage, this, mfm.obtenerTransaccion(this.transaccionSeleccionada.getCodigo()));
            stage.show();
            this.stage.close();
        }
        else {
            mostrarMensaje("Información Transaccion", "Transaccion No Seleccionada", "No se ha seleccionado ninguna transaccion", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void getFechaEvent(ActionEvent event) {
        try{
            LocalDate myDate = dateFecha.getValue();
            fechaTransaccion = myDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        catch(Exception ignored){

        }
    }

    @FXML
    void nuevaTransaccionEvent(ActionEvent event) {
        limpiarDatos();
    }

    @FXML
    void seleccionarTransaccionEvent(MouseEvent event) {
        this.transaccionSeleccionada = this.tableTransacciones.getSelectionModel().getSelectedItem();

        if(this.transaccionSeleccionada!=null) {
            inputCodigo.setText(this.transaccionSeleccionada.getCodigo());
            comboCliente.getSelectionModel().select(this.transaccionSeleccionada.getClienteTransaccion().getIdentificacion());
            dateFecha.setValue(LocalDate.parse(this.transaccionSeleccionada.getFecha(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))); this.fechaTransaccion=this.transaccionSeleccionada.getFecha();
            inputTotal.setDisable(false); inputTotal.setText("$"+" "+this.transaccionSeleccionada.getTotal());
            inputIVA.setDisable(false); inputIVA.setText("$"+" "+this.transaccionSeleccionada.getIva());
        }
    }

    @FXML
    void volverEvent(ActionEvent event) {
        ventanaPrincipalController.show();
        stage.close();
    }

    public void limpiarDatos(){
        inputCodigo.setText(null);
        comboCliente.getSelectionModel().clearSelection();
        dateFecha.setValue(null); this.fechaTransaccion = null;
        deshabilitarDatos();
    }
    public void deshabilitarDatos(){
        inputTotal.setDisable(true); inputTotal.setText(null);
        inputIVA.setDisable(true); inputIVA.setText(null);
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public void init(Stage stage, VentanaPrincipalController ventanaPrincipalController) {
        this.stage = stage;
        this.ventanaPrincipalController = ventanaPrincipalController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaTransaccionData = FXCollections.observableArrayList();

        this.columnCodigo.setCellValueFactory(new PropertyValueFactory<Transaccion, String>("codigo"));
        this.columnFecha.setCellValueFactory(new PropertyValueFactory<Transaccion, String>("fecha"));
        this.columnTotal.setCellValueFactory(new PropertyValueFactory<Transaccion, Double>("total"));
        this.columnIva.setCellValueFactory(new PropertyValueFactory<Transaccion, Double>("iva"));
        this.columnCliente.setCellValueFactory(new PropertyValueFactory<Transaccion, Cliente>("clienteTransaccion"));

        listaTransaccionData.addAll(ModelFactoryController.getInstance().obtenerTransacciones());
        tableTransacciones.setItems(listaTransaccionData);

        ObservableList<String> listaClientes = FXCollections.observableArrayList();
        listaClientes.addAll(mfm.obtenerIdentificacionesClientes());
        comboCliente.setItems(listaClientes);

        deshabilitarDatos();

    }

    private boolean validarDatos(String codigo, String fecha, String identificacionCliente) {
        String mensaje = "";

        if(codigo == null || codigo.isEmpty())
            mensaje += "El codigo es invalido \n";

        if(fecha == null || fecha.isEmpty())
            mensaje += "La fecha es invalida \n";

        if(identificacionCliente == null || identificacionCliente.isEmpty())
            mensaje += "La identificacion del cliente es invalida \n";

        if(mensaje.isEmpty()){
            return true;
        }else{
            mostrarMensaje("Información Cliente", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    public void show() {
        stage.show();
        tableTransacciones.refresh();
    }
}

