package co.uniquindio.almacen.model;

public class ClienteNatural extends Cliente{

    //Atributos de la clase ClienteNatural
    public String email;
    public String fechaNacimiento;

    /**
     * Metodo constructor vacio de la clase ClienteNatural
     */
    public ClienteNatural(){

    }

    /**
     * Metodo constructor de la clase ClienteNatural
     * @param nombre
     * @param apellido
     * @param identificacion
     * @param direccion
     * @param telefono
     * @param email
     * @param fechaNacimiento
     */
    public ClienteNatural(String nombre, String apellido, String identificacion, String direccion, String telefono, TipoCliente tipoCliente, String email, String fechaNacimiento) {
        super(nombre, apellido, identificacion, direccion, telefono, tipoCliente);
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    //getters() & setters() de la clase ClienteNatural
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    //toString() de la clase ClienteNatural
    @Override
    public String toString() {
        return "ClienteNatural{" +
                "email='" + email + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", identificacion='" + identificacion + '\'' +
                '}';
    }

}
