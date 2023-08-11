package co.uniquindio.almacen.model;

public class ProductoRefrigerado extends Producto{

    //Atributos de la clase ProductoRefrigerado
    public String codigoAprobacion;
    public String temperaturaRecomendada;

    /**
     * Constructor vacio de la clase ProductoRefrigerado
     */
    public ProductoRefrigerado(){

    }

    /**
     * Constructor de la clase ProductoRefrigerado
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param valorUnitario
     * @param cantidadInventario
     * @param codigoAprobacion
     * @param temperaturaRecomendada
     */
    public ProductoRefrigerado(String codigo, String nombre, String descripcion, double valorUnitario, int cantidadInventario, TipoProducto tipoProducto, String codigoAprobacion, String temperaturaRecomendada) {
        super(codigo, nombre, descripcion, valorUnitario, cantidadInventario, tipoProducto);
        this.codigoAprobacion = codigoAprobacion;
        this.temperaturaRecomendada = temperaturaRecomendada;
    }

    //getters() & setters() de la clase ProductoRefrigerado
    public String getCodigoAprobacion() {
        return codigoAprobacion;
    }
    public void setCodigoAprobacion(String codigoAprobacion) {
        this.codigoAprobacion = codigoAprobacion;
    }
    public String getTemperaturaRecomendada() {
        return temperaturaRecomendada;
    }
    public void setTemperaturaRecomendada(String temperaturaRecomendada) {
        this.temperaturaRecomendada = temperaturaRecomendada;
    }

    //toString() de la clase ProductoRefrigerado
    @Override
    public String toString() {
        return "ProductoRefrigerado{" +
                "codigoAprobacion='" + codigoAprobacion + '\'' +
                ", temperaturaRecomendada='" + temperaturaRecomendada + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", valorUnitario=" + valorUnitario +
                ", cantidadInventario=" + cantidadInventario +
                '}';
    }
}
