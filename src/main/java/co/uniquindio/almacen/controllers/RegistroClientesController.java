package co.uniquindio.almacen.controllers;

import co.uniquindio.almacen.exceptions.ObjectException;
import co.uniquindio.almacen.model.Cliente;
import co.uniquindio.almacen.model.ClienteJuridico;
import co.uniquindio.almacen.model.ClienteNatural;
import co.uniquindio.almacen.model.TipoCliente;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class RegistroClientesController implements Initializable {

    private Stage stage;
    private ModelFactoryController mfm = ModelFactoryController.getInstance();
    private ObservableList<Cliente> listaClientesData;
    private VentanaPrincipalController ventanaPrincipalController;
    private Cliente clienteSeleccionado;
    private String fechaNacimiento;

    @FXML
    private TextField inputNombres;

    @FXML
    private TextField inputApellidos;

    @FXML
    private TextField inputIdentificacion;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnActualizar;

    @FXML
    private TextField inputDireccion;

    @FXML
    private TextField inputTelefono;

    @FXML
    private Button btnBuscar;

    @FXML
    private ComboBox comboTipoCliente;

    @FXML
    private Label labelEmail;

    @FXML
    private TextField inputEmail;

    @FXML
    private Label labelNit;

    @FXML
    private TextField inputNit;

    @FXML
    private Label labelFechaNacimiento;

    @FXML
    private DatePicker dateFechaNacimiento;

    @FXML
    private TableView<Cliente> tableClientes;

    @FXML
    private TableColumn<Cliente, String> columnNombres;

    @FXML
    private TableColumn<Cliente, String> columnApellidos;

    @FXML
    private TableColumn<Cliente, String> columnIdentificacion;

    @FXML
    private TableColumn<Cliente, String> columnDireccion;

    @FXML
    private TableColumn<Cliente, String> columnTelefono;

    @FXML
    private TableColumn<Cliente, TipoCliente> columnTipoCliente;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVolver;

    @FXML
    void actualizarClienteEvent(ActionEvent event) {
        if(this.clienteSeleccionado!=null) {
            String nombre = inputNombres.getText(); String apellido = inputApellidos.getText(); String identificacion = inputIdentificacion.getText();
            String direccion = inputDireccion.getText(); String telefono = inputTelefono.getText(); TipoCliente tipoCliente = TipoCliente.valueOf(comboTipoCliente.getSelectionModel().getSelectedItem().toString());
            String email = inputEmail.getText(); String fechaNacimiento = this.fechaNacimiento; String nit = inputNit.getText();
            if(validarDatos(nombre, apellido, identificacion, direccion, telefono, tipoCliente, email, fechaNacimiento, nit)) {
                try {
                    String mensaje = mfm.actualizarCliente(nombre, apellido, identificacion, direccion, telefono, email, fechaNacimiento, nit);
                    mostrarMensaje("Cliente Actualizado", "Tarea Completada:", mensaje, Alert.AlertType.INFORMATION);

                    this.clienteSeleccionado = mfm.obtenerCliente(identificacion);
                    this.tableClientes.refresh();

                    inputNombres.setText(null); inputApellidos.setText(null); inputIdentificacion.setText(null);
                    inputDireccion.setText(null); inputTelefono.setText(null); comboTipoCliente.getSelectionModel().clearSelection();
                    deshabilitarDatosTipoCliente();

                    this.clienteSeleccionado = null;
                } catch (ObjectException e) {
                    mostrarMensaje("Error:", "Actualizacion Cliente:", e.getMessage(), Alert.AlertType.WARNING);
                }
            }
        }
        else {
            mostrarMensaje("Información Cliente", "Cliente No Seleccionado", "No se ha seleccionado ningun cliente", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void agregarClienteEvent(ActionEvent event) {
        String nombre = inputNombres.getText(); String apellido = inputApellidos.getText(); String identificacion = inputIdentificacion.getText();
        String direccion = inputDireccion.getText(); String telefono = inputTelefono.getText(); TipoCliente tipoCliente = TipoCliente.valueOf(comboTipoCliente.getSelectionModel().getSelectedItem().toString());
        String email = inputEmail.getText(); String fechaNacimiento = this.fechaNacimiento; String nit = inputNit.getText();

        if(validarDatos(nombre, apellido, identificacion, direccion, telefono, tipoCliente, email, fechaNacimiento, nit)) {

            try {
                String mensaje = mfm.crearCliente(nombre, apellido, identificacion, direccion, telefono, tipoCliente, email, fechaNacimiento, nit);
                mostrarMensaje("Cliente Creado", "Tarea Completada:", mensaje, Alert.AlertType.INFORMATION);

                Cliente c = mfm.obtenerCliente(identificacion);

                if(!this.listaClientesData.contains(c)) {
                    this.listaClientesData.add(c);
                    this.tableClientes.setItems(listaClientesData);

                    inputNombres.setText(null); inputApellidos.setText(null); inputIdentificacion.setText(null);
                    inputDireccion.setText(null); inputTelefono.setText(null); comboTipoCliente.getSelectionModel().clearSelection();
                    deshabilitarDatosTipoCliente();
                }

            } catch (ObjectException e) {
                mostrarMensaje("Error:", "Registro Cliente:", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    void buscarClienteEvent(ActionEvent event) {
        String identificacion = JOptionPane.showInputDialog("Ingrese la identificacion del cliente:");
        Cliente c = mfm.obtenerCliente(identificacion);
        if(c == null){
            mostrarMensaje("Error:", "Busquedad Cliente:", "El cliente no se encuentra registrado", Alert.AlertType.WARNING);
        }
        else{
            mostrarMensaje("Información Cliente::", "Busquedad Cliente:", c.toString(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void eliminarClienteEvent(ActionEvent event) {
        if(this.clienteSeleccionado!=null) {
            this.listaClientesData.remove(this.clienteSeleccionado);
            this.tableClientes.refresh();
            try {
                String mensaje = mfm.eliminarCliente(this.clienteSeleccionado.getIdentificacion());
                mostrarMensaje("Cliente Eliminado", "Tarea Completada:", mensaje, Alert.AlertType.INFORMATION);

                inputNombres.setText(null); inputApellidos.setText(null); inputIdentificacion.setText(null);
                inputDireccion.setText(null); inputTelefono.setText(null); comboTipoCliente.getSelectionModel().clearSelection();
                deshabilitarDatosTipoCliente();

                this.clienteSeleccionado = null;
            } catch (ObjectException e) {
                mostrarMensaje("Error:", "Eliminación Cliente:", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje("Información Cliente", "Cliente No Seleccionado", "No se ha seleccionado ningun cliente", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void getFechaNacimiento(ActionEvent event) {
        LocalDate myDate = dateFechaNacimiento.getValue();
        fechaNacimiento = myDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @FXML
    void nuevoClienteEvent(ActionEvent event) {
        inputNombres.setText(null);
        inputApellidos.setText(null);
        inputIdentificacion.setText(null);
        inputDireccion.setText(null);
        inputTelefono.setText(null);
        comboTipoCliente.getSelectionModel().clearSelection();
        deshabilitarDatosTipoCliente();
    }

    @FXML
    void refrescarAtributosTipoCliente(ActionEvent event) {
        try {
            if(comboTipoCliente.getSelectionModel().getSelectedItem().equals("NATURAL")){
                deshabilitarDatosTipoCliente();
                inputEmail.setDisable(false);
                dateFechaNacimiento.setDisable(false);
            }
            if(comboTipoCliente.getSelectionModel().getSelectedItem().equals("JURIDICO")){
                deshabilitarDatosTipoCliente();
                inputNit.setDisable(false);
            }
        }
        catch(Exception ignored) {

        }
    }

    @FXML
    void seleccionarClienteEvent(MouseEvent event) {
        this.clienteSeleccionado = this.tableClientes.getSelectionModel().getSelectedItem();

        if(this.clienteSeleccionado!=null) {
            inputNombres.setText(this.clienteSeleccionado.getNombre());
            inputApellidos.setText(this.clienteSeleccionado.getApellido());
            inputIdentificacion.setText(this.clienteSeleccionado.getIdentificacion());
            inputDireccion.setText(this.clienteSeleccionado.getDireccion());
            inputTelefono.setText(this.clienteSeleccionado.getTelefono());
            comboTipoCliente.getSelectionModel().select(this.clienteSeleccionado.getTipoCliente().toString());
            if(this.clienteSeleccionado.getTipoCliente().equals(TipoCliente.NATURAL)){
                ClienteNatural cn = (ClienteNatural) this.clienteSeleccionado;
                labelEmail.setVisible(true); inputEmail.setVisible(true); inputEmail.setText(cn.getEmail());
                labelFechaNacimiento.setVisible(true); dateFechaNacimiento.setVisible(true);
            }
            if(this.clienteSeleccionado.getTipoCliente().equals(TipoCliente.JURIDICO)){
                ClienteJuridico cj = (ClienteJuridico) this.clienteSeleccionado;
                labelNit.setVisible(true); inputNit.setVisible(true); inputNit.setText(cj.getNit());
            }
        }
    }

    @FXML
    void volverEvent(ActionEvent event) {
        ventanaPrincipalController.show();
        stage.close();
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
        listaClientesData = FXCollections.observableArrayList();

        this.columnNombres.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        this.columnApellidos.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        this.columnIdentificacion.setCellValueFactory(new PropertyValueFactory<Cliente, String>("identificacion"));
        this.columnDireccion.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccion"));
        this.columnTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
        this.columnTipoCliente.setCellValueFactory(new PropertyValueFactory<Cliente, TipoCliente>("tipoCliente"));

        listaClientesData.addAll(ModelFactoryController.getInstance().obtenerListClientes());
        tableClientes.setItems(listaClientesData);

        ObservableList<String> tipoClientes = FXCollections.observableArrayList("NATURAL", "JURIDICO");
        comboTipoCliente.setItems(tipoClientes);

        deshabilitarDatosTipoCliente();

    }

    public void deshabilitarDatosTipoCliente(){
        inputEmail.setDisable(true); inputEmail.setText(null);
        dateFechaNacimiento.setDisable(true);
        inputNit.setDisable(true); inputNit.setText(null);
    }

    private boolean validarDatos(String nombre, String apellido, String identificacion, String direccion, String telefono, TipoCliente tipoCliente, String email, String fechaNacimiento, String nit) {
        String mensaje = "";

        if(nombre == null || nombre.isEmpty())
            mensaje += "El nombre es invalido \n";

        if(apellido == null || apellido.isEmpty())
            mensaje += "El apellido es invalido \n";

        if(identificacion == null || identificacion.isEmpty())
            mensaje += "La identificacion es invalida \n";

        if(direccion == null || direccion.isEmpty())
            mensaje += "La direccion es invalida \n";

        if(telefono == null || telefono.isEmpty())
            mensaje += "El telefono es invalido \n";

        if(tipoCliente == null || (!tipoCliente.equals(TipoCliente.NATURAL) && !tipoCliente.equals(TipoCliente.JURIDICO)))
            mensaje += "El tipo de cliente es invalido \n";

        assert tipoCliente != null;
        if((tipoCliente.equals(TipoCliente.NATURAL))&&(email == null || email.isEmpty()))
            mensaje += "El email es invalido \n";

        if((tipoCliente.equals(TipoCliente.NATURAL))&&(fechaNacimiento == null || fechaNacimiento.isEmpty()))
            mensaje += "La fecha de nacimiento es invalida \n";

        if((tipoCliente.equals(TipoCliente.JURIDICO))&&(nit == null || nit.isEmpty()))
            mensaje += "El nit es invalido \n";

        if(mensaje.isEmpty()){
            return true;
        }else{
            mostrarMensaje("Información Cliente", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }

    }
}


