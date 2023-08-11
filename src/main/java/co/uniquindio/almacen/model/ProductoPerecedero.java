package co.uniquindio.almacen.model;

public class ProductoPerecedero extends Producto{

    //Atributos de la clase ProductoPerecedero
    public String fechaVencimiento;

    /**
     * Constructor vacio de la clase ProductoPerecedero
     */
    public ProductoPerecedero(){

    }

    /**
     * Constructor de la clase ProductoPerecedero
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param valorUnitario
     * @param cantidadInventario
     * @param fechaVencimiento
     */
    public ProductoPerecedero(String codigo, String nombre, String descripcion, double valorUnitario, int cantidadInventario, TipoProducto tipoProducto, String fechaVencimiento) {
        super(codigo, nombre, descripcion, valorUnitario, cantidadInventario, tipoProducto);
        this.fechaVencimiento = fechaVencimiento;
    }

    //getters() & setters() de la clase ProductoPerecedero
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    //toString() de la clase ProductoPerecedero
    @Override
    public String toString() {
        return "ProductoPerecedero{" +
                "fechaVencimiento='" + fechaVencimiento + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", valorUnitario=" + valorUnitario +
                ", cantidadInventario=" + cantidadInventario +
                '}';
    }
}
