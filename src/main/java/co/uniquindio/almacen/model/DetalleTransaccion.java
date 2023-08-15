package co.uniquindio.almacen.model;

public class DetalleTransaccion {

    //Atributos de la clase DetalleTransaccion
    public Transaccion transaccion;
    public Producto producto;
    public int cantidadProducto;
    public double subtotal;

    /**
     * Constructor vacio de la clase DetalleTransaccion
     */
    public DetalleTransaccion(){

    }

    /**
     * Contrcutor de la clase DetalleTransaccion
     * @param transaccion
     * @param producto
     * @param cantidadProducto
     * @param subtotal
     */
    public DetalleTransaccion(Transaccion transaccion, Producto producto, int cantidadProducto, double subtotal) {
        this.transaccion = transaccion;
        this.producto = producto;
        this.cantidadProducto = cantidadProducto;
        this.subtotal = subtotal;
    }

    //getters() & setters() de la clase DetalleTransaccion
    public Transaccion getTransaccion() {
        return transaccion;
    }
    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public int getCantidadProducto() {
        return cantidadProducto;
    }
    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    //toString() de la clase DetalleTransaccion
    @Override
    public String toString() {
        return "DetalleTransaccion{" +
                "transaccion=" + transaccion +
                ", producto=" + producto +
                ", cantidadProducto=" + cantidadProducto +
                ", subtotal=" + subtotal +
                '}';
    }

    /**
     * Metodo para regresar la cantidad de produto en el Detalle de Venta
     * Solo se utiliza cuando se elimina un Detalle de Venta
     */
    public void devolverInventario() {
        producto.setCantidadInventario(producto.getCantidadInventario()+cantidadProducto);
    }
}
