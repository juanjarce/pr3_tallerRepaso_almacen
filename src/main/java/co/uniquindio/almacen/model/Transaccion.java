package co.uniquindio.almacen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Transaccion {

    //Atributos de la clase Transaccion
    public String codigo;
    public String fecha;
    public double total;
    public double iva;
    public Cliente clienteTransaccion;
    public List<DetalleTransaccion> listaDetalleTransaccion;

    /**
     * Constructor de la clase Transaccion
     */
    public Transaccion(){

    }

    /**
     * Constructor de la clase Transaccion
     * @param codigo
     * @param fecha
     * @param clienteTransaccion
     */
    public Transaccion(String codigo, String fecha, Cliente clienteTransaccion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.total = 0.0;
        this.iva = 0.0;
        this.clienteTransaccion = clienteTransaccion;
        this.listaDetalleTransaccion = new ArrayList<DetalleTransaccion>();
    }

    //getters() & setters() de la clase Transaccion
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public double getIva() {
        return iva;
    }
    public void setIva(double iva) {
        this.iva = iva;
    }
    public Cliente getClienteTransaccion() {
        return clienteTransaccion;
    }
    public void setClienteTransaccion(Cliente clienteTransaccion) {
        this.clienteTransaccion = clienteTransaccion;
    }
    public List<DetalleTransaccion> getListaDetalleTransaccion() {
        return listaDetalleTransaccion;
    }
    public void setListaDetalleTransaccion(List<DetalleTransaccion> listaDetalleTransaccion) {
        this.listaDetalleTransaccion = listaDetalleTransaccion;
    }

    //toString() de la clase Transaccion
    @Override
    public String toString() {
        return "Transaccion{" +
                "codigo='" + codigo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", total=" + total +
                ", iva=" + iva +
                '}';
    }

    //hashCode() & equals() de la clase Transaccion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaccion that = (Transaccion) o;
        return Objects.equals(codigo, that.codigo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    //Metodos en Transaccion

    /**
     * Metodo para añadir un detalle de transaccion en la lista de detalles de una Transaccion
     * @param detalle
     */
    public void aniadirDetalleTransaccion(DetalleTransaccion detalle) {
        listaDetalleTransaccion.add(detalle);
    }

    /**
     * Metodo para modifcar el total e iva de una Transaccion
     * El metodo solo se usa despues de realizar un Detalle de Transaccion
     * @param subtotal
     */
    public void modificarTotal(double subtotal) {
        setTotal(getTotal()+subtotal);
        setIva(getTotal()*0.19);
    }

    /**
     * Metodo para devolver los productos de cada Detalle de Venta
     */
    public void devolverInventario() {
        listaDetalleTransaccion.forEach(DetalleTransaccion::devolverInventario);
    }
}
