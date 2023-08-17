package co.uniquindio.almacen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Producto {

    //Atributos de la clase Producto
    public String codigo;
    public String nombre;
    public String descripcion;
    public double valorUnitario;
    public int cantidadInventario;
    public TipoProducto tipoProducto;
    public List<DetalleTransaccion> listaDetallesTransaccion;

    /**
     * Metodo constructor vacio de la clase Producto
     */
    public Producto(){

    }

    /**
     * Metodo constructor de la clase Producto
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param valorUnitario
     * @param cantidadInventario
     */
    public Producto(String codigo, String nombre, String descripcion, double valorUnitario, int cantidadInventario, TipoProducto tipoProducto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorUnitario = valorUnitario;
        this.cantidadInventario = cantidadInventario;
        this.tipoProducto = tipoProducto;
        this.listaDetallesTransaccion = new ArrayList<DetalleTransaccion>();
    }

    //getters() & setters() de la clase Producto
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public int getCantidadInventario() {
        return cantidadInventario;
    }
    public void setCantidadInventario(int cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }
    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }
    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    public List<DetalleTransaccion> getListaDetallesTransaccion() {
        return listaDetallesTransaccion;
    }
    public void setListaDetallesTransaccion(List<DetalleTransaccion> listaDetallesTransaccion) {
        this.listaDetallesTransaccion = listaDetallesTransaccion;
    }

    //toString() de la clase Producto


    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", valorUnitario=" + valorUnitario +
                ", cantidadInventario=" + cantidadInventario +
                ", tipoProducto=" + tipoProducto +
                ", listaDetallesTransaccion=" + listaDetallesTransaccion +
                '}';
    }

    //hashCode() & equals() de la clase Producto
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo, producto.codigo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    //-----------------------------------------------------------------------------------------------------------------------------
    //Metodos de la clase Producto

    /**
     * Metodo que verifica si hay la cantidad requerida de un producto en el invetario
     * @param cantidadProducto
     * @return
     */
    public boolean verificarExistencias(int cantidadProducto) {
        return getCantidadInventario() >= cantidadProducto;
    }

}
