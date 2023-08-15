package co.uniquindio.almacen.controllers;

import co.uniquindio.almacen.exceptions.ObjectException;
import co.uniquindio.almacen.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.security.auth.callback.TextInputCallback;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class RegistroProductosController implements Initializable {

    private Stage stage;
    private ModelFactoryController mfm = ModelFactoryController.getInstance();
    private ObservableList<Producto> listaProductosData;
    private VentanaPrincipalController ventanaPrincipalController;
    private Producto productoSeleccionado;
    private String fechaVencimiento;
    private String fechaEnvasado;

    @FXML
    private TextField inputNombre;

    @FXML
    private TextField inputCodigo;

    @FXML
    private TextField inputValorUnitario;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnActualizar;

    @FXML
    private ComboBox comboTipoProducto;

    @FXML
    private TextField inputCantidadInventario;

    @FXML
    private TextArea inputDescripcion;

    @FXML
    private Label labelFechaVencimiento;

    @FXML
    private DatePicker dateFechaVencimiento;

    @FXML
    private Label labelCodigoAprobacion;

    @FXML
    private Label labelTemperatura;

    @FXML
    private TextField inputCodigoAprobacion;

    @FXML
    private TextField inputTemperatura;

    @FXML
    private Label labelFechaEnvasado;

    @FXML
    private Label labelPeso;

    @FXML
    private Label labelPais;

    @FXML
    private DatePicker dateFechaEnvasado;

    @FXML
    private TextField inputPeso;

    @FXML
    private ComboBox comboPais;

    @FXML
    private TableView<Producto> tableProducto;

    @FXML
    private TableColumn<Producto, String> columnNombre;

    @FXML
    private TableColumn<Producto, String> columnCodigo;

    @FXML
    private TableColumn<Producto, Double> columnValor;

    @FXML
    private TableColumn<Producto, Integer> columnInventario;

    @FXML
    private TableColumn<Producto, TipoProducto> columnTipoProducto;

    @FXML
    private TableColumn<Producto, String> columnDescripcion;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVolver;

    @FXML
    void actualizarProductoEvent(ActionEvent event) {
        if (this.productoSeleccionado != null) {
            String nombre = inputNombre.getText(); String codigo = inputCodigo.getText(); String descripcion = inputDescripcion.getText();
            String valorUnitario = inputValorUnitario.getText(); String cantidadInventario = inputCantidadInventario.getText();
            String tipoProducto = comboTipoProducto.getSelectionModel().getSelectedItem().toString(); String pesoEnvase = inputPeso.getText();
            String paisOrigen = comboPais.getAccessibleText(); if(tipoProducto.equals("ENVASADO")) paisOrigen = comboPais.getSelectionModel().getSelectedItem().toString();
            String codigoAprobacion = inputCodigoAprobacion.getText(); String temperaturaRecomendada = inputTemperatura.getText();

            if (validarDatos(codigo, nombre, descripcion, valorUnitario, cantidadInventario, tipoProducto, fechaEnvasado, pesoEnvase, paisOrigen,
                    fechaVencimiento, codigoAprobacion, temperaturaRecomendada)) {
                double pesoEnvase1 = 0.0; if(tipoProducto.equals("ENVASADO")) pesoEnvase1 = Double.parseDouble(pesoEnvase);
                PaisOrigen paisOrigen1 = null; if(tipoProducto.equals("ENVASADO")) paisOrigen1 = PaisOrigen.valueOf(comboPais.getSelectionModel().getSelectedItem().toString());

                try {
                    String mensaje = mfm.actualizarProducto(codigo, nombre, descripcion, Double.parseDouble(valorUnitario), Integer.parseInt(cantidadInventario),
                            TipoProducto.valueOf(comboTipoProducto.getSelectionModel().getSelectedItem().toString()), fechaEnvasado, pesoEnvase1,
                            paisOrigen1, fechaVencimiento, codigoAprobacion, temperaturaRecomendada);
                    mostrarMensaje("Producto Actualizado", "Tarea Completada:", mensaje, Alert.AlertType.INFORMATION);

                    this.tableProducto.refresh();

                    inputNombre.setText(null); inputCodigo.setText(null); inputDescripcion.setText(null);
                    inputValorUnitario.setText(null); inputCantidadInventario.setText(null); comboTipoProducto.getSelectionModel().clearSelection();
                    ocultarDatosTipoProducto();

                    this.productoSeleccionado = null;
                } catch (ObjectException e) {
                    mostrarMensaje("Error:", "Actualizacion Producto:", e.getMessage(), Alert.AlertType.WARNING);
                }
            }
        }
        else{
            mostrarMensaje("Error:", "Produvto No Seleccionado:", "No se ha seleccionado un producto", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void agregarProductoEvent(ActionEvent event) {
        String nombre = inputNombre.getText(); String codigo = inputCodigo.getText(); String descripcion = inputDescripcion.getText();
        String valorUnitario = inputValorUnitario.getText(); String cantidadInventario = inputCantidadInventario.getText();
        String tipoProducto = comboTipoProducto.getSelectionModel().getSelectedItem().toString(); String pesoEnvase = inputPeso.getText();
        String paisOrigen = comboPais.getAccessibleText(); if(tipoProducto.equals("ENVASADO")) paisOrigen = comboPais.getSelectionModel().getSelectedItem().toString();
        String codigoAprobacion = inputCodigoAprobacion.getText(); String temperaturaRecomendada = inputTemperatura.getText();

        if(validarDatos(codigo, nombre, descripcion, valorUnitario, cantidadInventario, tipoProducto, fechaEnvasado, pesoEnvase, paisOrigen,
                fechaVencimiento, codigoAprobacion, temperaturaRecomendada)) {
            double pesoEnvase1 = 0.0; if(tipoProducto.equals("ENVASADO")) pesoEnvase1 = Double.parseDouble(pesoEnvase);
            PaisOrigen paisOrigen1 = null; if(tipoProducto.equals("ENVASADO")) paisOrigen1 = PaisOrigen.valueOf(comboPais.getSelectionModel().getSelectedItem().toString());

            try {
                String mensaje = mfm.crearProducto(codigo, nombre, descripcion, Double.parseDouble(valorUnitario), Integer.parseInt(cantidadInventario),
                        TipoProducto.valueOf(comboTipoProducto.getSelectionModel().getSelectedItem().toString()), fechaEnvasado, pesoEnvase1,
                        paisOrigen1, fechaVencimiento, codigoAprobacion, temperaturaRecomendada);
                mostrarMensaje("Producto Creado", "Tarea Completada:", mensaje, Alert.AlertType.INFORMATION);

                Producto p = mfm.obtenerProducto(codigo);

                if(!this.listaProductosData.contains(p)) {
                    this.listaProductosData.add(p);
                    this.tableProducto.setItems(listaProductosData);

                    inputNombre.setText(null); inputCodigo.setText(null); inputDescripcion.setText(null);
                    inputValorUnitario.setText(null); inputCantidadInventario.setText(null); comboTipoProducto.getSelectionModel().clearSelection();
                    ocultarDatosTipoProducto();
                }

            } catch (ObjectException e) {
                mostrarMensaje("Error:", "Registro Producto:", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    void eliminarProductoEvent(ActionEvent event) {
        if(this.productoSeleccionado!=null) {
            this.listaProductosData.remove(this.productoSeleccionado);
            this.tableProducto.refresh();
            try {
                String mensaje = mfm.eliminarProducto(this.productoSeleccionado.getCodigo());
                mostrarMensaje("Producto Eliminado", "Tarea Completada:", mensaje, Alert.AlertType.INFORMATION);

                inputNombre.setText(null); inputCodigo.setText(null); inputDescripcion.setText(null);
                inputValorUnitario.setText(null); inputCantidadInventario.setText(null); comboTipoProducto.getSelectionModel().clearSelection();
                ocultarDatosTipoProducto();

                this.productoSeleccionado = null;
            } catch (ObjectException e) {
                mostrarMensaje("Error:", "Eliminación Producto:", e.getMessage(), Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje("Información Producto", "Producto No Seleccionado", "No se ha seleccionado ningun producto", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void getFechaEnvasado(ActionEvent event) {
        LocalDate myDate = dateFechaEnvasado.getValue();
        fechaEnvasado = myDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @FXML
    void getFechaVencimiento(ActionEvent event) {
        LocalDate myDate = dateFechaVencimiento.getValue();
        fechaVencimiento = myDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @FXML
    void nuevoProductoEvent(ActionEvent event) {
        inputNombre.setText(null);
        inputCodigo.setText(null);
        inputDescripcion.setText(null);
        inputValorUnitario.setText(null);
        inputCantidadInventario.setText(null);
        comboTipoProducto.getSelectionModel().clearSelection();
        ocultarDatosTipoProducto();
    }

    @FXML
    void refrescarAtributosTipoProducto(ActionEvent event) {
        try {
            if(comboTipoProducto.getSelectionModel().getSelectedItem().equals("PERECEDERO")){
                ocultarDatosTipoProducto();
                labelFechaVencimiento.setVisible(true); dateFechaVencimiento.setVisible(true);
            }
            if(comboTipoProducto.getSelectionModel().getSelectedItem().equals("REFRIGERADO")){
                ocultarDatosTipoProducto();
                labelCodigoAprobacion.setVisible(true); inputCodigoAprobacion.setVisible(true);
                labelTemperatura.setVisible(true); inputTemperatura.setVisible(true);
            }
            if(comboTipoProducto.getSelectionModel().getSelectedItem().equals("ENVASADO")){
                ocultarDatosTipoProducto();
                labelFechaEnvasado.setVisible(true); dateFechaEnvasado.setVisible(true);
                labelPeso.setVisible(true); inputPeso.setVisible(true);
                labelPais.setVisible(true); comboPais.setVisible(true);
            }
        }
        catch(Exception ignored) {

        }
    }

    @FXML
    void seleccionarProductoEvent(MouseEvent event) {
        this.productoSeleccionado = this.tableProducto.getSelectionModel().getSelectedItem();

        if(this.productoSeleccionado!=null) {
            inputNombre.setText(this.productoSeleccionado.getNombre());
            inputCodigo.setText(this.productoSeleccionado.getCodigo());
            inputDescripcion.setText(this.productoSeleccionado.getDescripcion());
            inputValorUnitario.setText(Double.toString(this.productoSeleccionado.getValorUnitario()));
            inputCantidadInventario.setText(Integer.toString(this.productoSeleccionado.getCantidadInventario()));
            comboTipoProducto.getSelectionModel().select(this.productoSeleccionado.getTipoProducto().toString());
            if(this.productoSeleccionado.getTipoProducto().equals(TipoProducto.ENVASADO)){
                ProductoEnvasado pe = (ProductoEnvasado) this.productoSeleccionado;
                labelFechaEnvasado.setVisible(true); dateFechaEnvasado.setVisible(true);
                labelPeso.setVisible(true); inputPeso.setVisible(true); inputPeso.setText(Double.toString(pe.getPesoEnvase()));
                labelPais.setVisible(true); comboTipoProducto.setVisible(true); comboPais.getSelectionModel().select(pe.getPaisOrigen());
            }
            if(this.productoSeleccionado.getTipoProducto().equals(TipoProducto.PERECEDERO)){
                labelFechaVencimiento.setVisible(true); dateFechaVencimiento.setVisible(true);
            }
            if(this.productoSeleccionado.getTipoProducto().equals(TipoProducto.REFRIGERADO)){
                ProductoRefrigerado pr = (ProductoRefrigerado) this.productoSeleccionado;
                labelCodigoAprobacion.setVisible(true); inputCodigoAprobacion.setVisible(true); inputCodigoAprobacion.setText(((ProductoRefrigerado) this.productoSeleccionado).getCodigoAprobacion());
                labelTemperatura.setVisible(true); inputTemperatura.setVisible(true); inputTemperatura.setText(((ProductoRefrigerado) this.productoSeleccionado).getTemperaturaRecomendada());
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
        listaProductosData = FXCollections.observableArrayList();

        this.columnNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        this.columnCodigo.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigo"));
        this.columnValor.setCellValueFactory(new PropertyValueFactory<Producto, Double>("valorUnitario"));
        this.columnInventario.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("cantidadInventario"));
        this.columnTipoProducto.setCellValueFactory(new PropertyValueFactory<Producto, TipoProducto>("tipoProducto"));
        this.columnDescripcion.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));

        listaProductosData.addAll(ModelFactoryController.getInstance().obtenerListProductos());
        tableProducto.setItems(listaProductosData);

        ObservableList<String> tipoProductos = FXCollections.observableArrayList("ENVASADO", "PERECEDERO", "REFRIGERADO");
        comboTipoProducto.setItems(tipoProductos);

        ObservableList<String> paisesOrigen = FXCollections.observableArrayList("COLOMBIA", "ARGENTINA", "CHILE", "ECUADOR", "PERU");
        comboPais.setItems(paisesOrigen);

        ocultarDatosTipoProducto();

    }

    public void ocultarDatosTipoProducto(){
        labelFechaVencimiento.setVisible(false); dateFechaVencimiento.setVisible(false);
        labelCodigoAprobacion.setVisible(false); inputCodigoAprobacion.setVisible(false); inputCodigoAprobacion.setText(null);
        labelTemperatura.setVisible(false); inputTemperatura.setVisible(false); inputTemperatura.setText("°C");
        labelFechaEnvasado.setVisible(false); dateFechaEnvasado.setVisible(false);
        labelPeso.setVisible(false); inputPeso.setVisible(false); inputPeso.setText(null);
        labelPais.setVisible(false); comboPais.setVisible(false); comboPais.getSelectionModel().clearSelection();
    }

    private boolean validarDatos(String codigo, String nombre, String descripcion, String valorUnitario, String cantidadInventario, String tipoProducto, String fechaEnvasado, String pesoEnvase, String paisOrigen,
                                 String fechaVencimiento, String codigoAprobacion, String temperaturaRecomendada) {
        String mensaje = "";

        if(nombre == null || nombre.isEmpty())
            mensaje += "El nombre es invalido \n";

        if(codigo == null || codigo.isEmpty())
            mensaje += "El codigo es invalido \n";

        if(descripcion == null || descripcion.isEmpty())
            mensaje += "La descripcion es invalida \n";

        if(valorUnitario == null || valorUnitario.isEmpty())
            mensaje += "El valor unitario es invalido \n";

        if(cantidadInventario == null || cantidadInventario.isEmpty())
            mensaje += "La cantidad de inventario es invalida \n";

        if(tipoProducto == null || (!tipoProducto.equals("ENVASADO") && !tipoProducto.equals("PERECEDERO") && !tipoProducto.equals("REFRIGERADO")))
            mensaje += "El tipo de producto es invalido \n";

        assert tipoProducto != null;
        if((tipoProducto.equals("PERECEDERO")) && (fechaVencimiento == null || fechaVencimiento.isEmpty()))
            mensaje += "La fecha de vencimiento es invalida \n";

        if((tipoProducto.equals("REFRIGERADO")) && (codigoAprobacion == null || codigoAprobacion.isEmpty()))
            mensaje += "El codigo de aprobacion es invalido \n";

        if((tipoProducto.equals("REFRIGERADO"))&&(temperaturaRecomendada == null || temperaturaRecomendada.isEmpty()))
            mensaje += "La temperatura recomendada es invalida \n";

        if((tipoProducto.equals("ENVASADO"))&&(fechaEnvasado == null || fechaEnvasado.isEmpty()))
            mensaje += "La fecha de envasado es invalida \n";

        if((tipoProducto.equals("ENVASADO"))&&(pesoEnvase == null || pesoEnvase.isEmpty()))
            mensaje += "El peso del envase es invalido \n";

        if((tipoProducto.equals("ENVASADO"))&&(!paisOrigen.equals("COLOMBIA") && !paisOrigen.equals("ARGENTINA") && !paisOrigen.equals("CHILE")
        && !paisOrigen.equals("ECUADOR") && !paisOrigen.equals("PERU")))
            mensaje += "El pais de origen es invalido \n";

        if(mensaje.isEmpty()){
            return true;
        }else{
            mostrarMensaje("Información Producto", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }

    }

}

