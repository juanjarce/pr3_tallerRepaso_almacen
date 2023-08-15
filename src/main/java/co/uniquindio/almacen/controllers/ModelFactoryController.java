package co.uniquindio.almacen.controllers;

import co.uniquindio.almacen.exceptions.ObjectException;
import co.uniquindio.almacen.model.*;

import java.util.List;

public class ModelFactoryController {

	Almacen almacen;

	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	public ModelFactoryController() {
		System.out.println("invocaci�n clase singleton");
		inicializarDatos();
	}

	private void inicializarDatos() {

		almacen = new Almacen("UQ", "Carrera 15 #12N", "123456789");

	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public List<Producto> obtenerListProductos() {
		return almacen.getListaProductos();
	}

	public String crearProducto(String codigo, String nombre, String descripcion, double valorUnitario, int cantidadInventario, TipoProducto tipoProducto, String fechaEnvasado, double pesoEnvase, PaisOrigen paisOrigen,
							  String fechaVencimiento, String codigoAprobacion, String temperaturaRecomendada) throws ObjectException {
		return almacen.crearProducto(codigo, nombre, descripcion, valorUnitario, cantidadInventario, tipoProducto, fechaEnvasado, pesoEnvase, paisOrigen,
				fechaVencimiento, codigoAprobacion, temperaturaRecomendada);
	}

	public String actualizarProducto(String codigo, String nombre, String descripcion, double valorUnitario, int cantidadInventario, TipoProducto tipoProducto, String fechaEnvasado, double pesoEnvase, PaisOrigen paisOrigen,
								   String fechaVencimiento, String codigoAprobacion, String temperaturaRecomendada) throws ObjectException {
		return almacen.actualizarProducto(codigo, nombre, descripcion, valorUnitario, cantidadInventario, fechaEnvasado, pesoEnvase, paisOrigen,
				fechaVencimiento, codigoAprobacion, temperaturaRecomendada);
		}

	public String  eliminarProducto(String codigo) throws ObjectException {
		return almacen.eliminarProducto(codigo);
	}

	public List<Cliente> obtenerListClientes() {
		return almacen.getListaClientes();
	}

	public Cliente obtenerCliente(String identificacion) {
		return almacen.obtenerCliente(identificacion);
	}

	public String crearCliente(String nombre, String apellido, String identificacion, String direccion, String telefono, TipoCliente tipoCliente, String email, String fechaNacimiento, String nit) throws ObjectException {
		return almacen.crearCliente(nombre, apellido, identificacion, direccion, telefono, tipoCliente, email, fechaNacimiento, nit);
	}

	public String actualizarCliente(String nombre, String apellido, String identificacion, String direccion, String telefono, String email, String fechaNacimiento, String nit) throws ObjectException {
		return almacen.actualizarCliente(nombre, apellido, identificacion, direccion, telefono, email, fechaNacimiento, nit);
	}

	public String eliminarCliente(String identificacion) throws ObjectException {
		return almacen.eliminarCliente(identificacion);
	}

	public Transaccion obtenerTransaccion(String codigo){
		return almacen.obtenerTransaccion(codigo);
	}

	public String realizarDetalleTransaccion(String codigoTransaccion, String codigoProducto, int cantidadProducto) throws ObjectException {
		return almacen.realizarDetalleTransaccion(codigoTransaccion, codigoProducto, cantidadProducto);
	}

	public String realizarTransaccion(String codigo, String fecha, String identificacionCliente) throws ObjectException {
		return almacen.realizarTransaccion(codigo, fecha, identificacionCliente);
	}

	public String eliminarTransaccion(String codigo) throws ObjectException {
		return almacen.eliminarTransaccion(codigo);
	}

	public Producto obtenerProducto(String codigo) {
		return almacen.obtenerProducto(codigo);
	}

	public List<Transaccion> obtenerTransacciones() {
		return almacen.getListaTransacciones();
	}

	public List<String> obtenerIdentificacionesClientes(){
		return almacen.devolverIdentificacionesClientes();
	}

	public List<String> obtenerCodigosProductos() {
		return almacen.devolverCodigosProductos();
	}

}

