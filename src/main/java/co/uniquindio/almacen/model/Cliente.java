package co.uniquindio.almacen.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente  extends Persona{

    //Atributos de la clase Cliente
    public String direccion;
    public String telefono;
    public TipoCliente tipoCliente;
    public List<Transaccion> listaTransacciones;

    /**
     * Metodo constructor vacio de la clase Cliente
     */
    public Cliente(){

    }

    /**
     * Metodo Constructo de la clase Cliente
     * @param nombre
     * @param apellido
     * @param identificacion
     * @param direccion
     * @param telefono
     */
    public Cliente(String nombre, String apellido, String identificacion, String direccion, String telefono, TipoCliente tipoCliente) {
        super(nombre, apellido, identificacion);
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
        this.listaTransacciones = new ArrayList<Transaccion>();
    }

    //getters() & setters() de la clase Cliente
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public List<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }
    public void setListaTransacciones(List<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    //toString() de la clase Cliente
    @Override
    public String toString() {
        return "Cliente{" +
                "direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipoCliente=" + tipoCliente +
                ", listaTransacciones=" + listaTransacciones +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", identificacion='" + identificacion + '\'' +
                '}';
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    //Metodos Cliente

    /**
     * Metodo para añadir una Transaccion a la lista de transacciones de un cliente
     * @param transaccion
     */
    public void aniadirTransaccion(Transaccion transaccion) {

        listaTransacciones.add(transaccion);
    }
}
