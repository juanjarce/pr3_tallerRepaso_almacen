package co.uniquindio.almacen.model;

import java.util.Objects;

public abstract class Persona {

    //Atributos de la clase Persona
    public String nombre;
    public String apellido;
    public String identificacion;

    /**
     * Metodo constructo vacio de la clase Persona
     */
    public Persona(){

    }

    /**
     * Metodo constructor de la clase Persona
     * @param nombre
     * @param apellido
     * @param identificacion
     */
    public Persona(String nombre, String apellido, String identificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
    }

    //getters() & setters() de la clase Persona
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    //toString() de la clase Persona
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", identificacion='" + identificacion + '\'' +
                '}';
    }

    //hashCode() & equals() de la clase Persona
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(identificacion, persona.identificacion);
    }
    @Override
    public int hashCode() {
        return Objects.hash(identificacion);
    }

}
