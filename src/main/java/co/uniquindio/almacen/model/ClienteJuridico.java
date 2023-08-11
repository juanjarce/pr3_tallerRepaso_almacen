package co.uniquindio.almacen.model;

public class ClienteJuridico  extends Cliente{

    //Atributos de la clase ClienteJuridico
    public String nit;

    /**
     * Metodo contructor vacio de la clase CLienteJuridico
     */
    public ClienteJuridico(){

    }

    /**
     * Metodo constructor de la clase ClienteJuridico
     * @param nombre
     * @param apellido
     * @param identificacion
     * @param direccion
     * @param telefono
     * @param nit
     */
    public ClienteJuridico(String nombre, String apellido, String identificacion, String direccion, String telefono, TipoCliente tipoCliente, String nit) {
        super(nombre, apellido, identificacion, direccion, telefono, tipoCliente);
        this.nit = nit;
    }

    //getters() & setters() de la clase ClienteJuridico
    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }

    //toString() de la clase ClienteJuridico
    @Override
    public String toString() {
        return "ClienteJuridico{" +
                "nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", identificacion='" + identificacion + '\'' +
                '}';
    }
}
