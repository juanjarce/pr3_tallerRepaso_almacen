package co.uniquindio.almacen.model;

public class ProductoEnvasado extends Producto{

    //Atributos de la clase ProductoEnvasado
    public String fechaEnvasado;
    public double pesoEnvase;
    public PaisOrigen paisOrigen;

    /**
     * Constructor vacio de la clase ProductoEnvasado
     */
    public ProductoEnvasado(){

    }

    /**
     * Constructor de la clase ProductoEnvasado
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param valorUnitario
     * @param cantidadInventario
     * @param fechaEnvasado
     * @param pesoEnvase
     * @param paisOrigen
     */
    public ProductoEnvasado(String codigo, String nombre, String descripcion, double valorUnitario, int cantidadInventario, TipoProducto tipoProducto, String fechaEnvasado, double pesoEnvase, PaisOrigen paisOrigen) {
        super(codigo, nombre, descripcion, valorUnitario, cantidadInventario, tipoProducto);
        this.fechaEnvasado = fechaEnvasado;
        this.pesoEnvase = pesoEnvase;
        this.paisOrigen = paisOrigen;
    }

    //getters() & setters() de la clase ProductoEnvasado
    public String getFechaEnvasado() {
        return fechaEnvasado;
    }
    public void setFechaEnvasado(String fechaEnvasado) {
        this.fechaEnvasado = fechaEnvasado;
    }
    public double getPesoEnvase() {
        return pesoEnvase;
    }
    public void setPesoEnvase(double pesoEnvase) {
        this.pesoEnvase = pesoEnvase;
    }
    public PaisOrigen getPaisOrigen() {
        return paisOrigen;
    }
    public void setPaisOrigen(PaisOrigen paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    //toString() de la clase ProductoEnvasado
    @Override
    public String toString() {
        return "ProductoEnvasado{" +
                "fechaEnvasado='" + fechaEnvasado + '\'' +
                ", pesoEnvase=" + pesoEnvase +
                ", paisOrigen=" + paisOrigen +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", valorUnitario=" + valorUnitario +
                ", cantidadInventario=" + cantidadInventario +
                '}';
    }
}
