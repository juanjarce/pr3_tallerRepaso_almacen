package co.uniquindio.almacen.model;

import co.uniquindio.almacen.exceptions.ObjectException;

import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;

public class Almacen {

    //Atributos de la clase Almacen
    public String nombre;
    public String direccion;
    public String telefono;
    public List<Persona> listaPersonas;
    public List<Transaccion> listaTransacciones;
    public List<Producto> listaProductos;

    /**
     * Metodo constructor vacio de la clase Almacen
     */
    public Almacen(){

    }

    /**
     * Metodo contructor de la clase Almacen
     * @param nombre
     * @param direccion
     * @param telefono
     */
    public Almacen(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.listaPersonas = new ArrayList<Persona>();
        this.listaProductos = new ArrayList<Producto>();
        this.listaTransacciones = new ArrayList<Transaccion>();
    }

    //getters() & setters() de la clase Almacen

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
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
    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }
    public void setListaPersonas(List<Persona> listaClientes) {
        this.listaPersonas = listaClientes;
    }
    public List<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }
    public void setListaTransacciones(List<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }
    public List<Producto> getListaProductos() {
        return listaProductos;
    }
    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    //toString() de la clase Almacen
    @Override
    public String toString() {
        return "Almacen{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", listaClientes=" + listaPersonas +
                ", listaTransacciones=" + listaTransacciones +
                ", listaProductos=" + listaProductos +
                '}';
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Metodos de la clase Almacen

    //CRUD Cliente
    /**
     * Metodo para obtener a un Cliente en la lista de personas del Almacen
     * @param identificacion
     * @return
     */
    public Cliente obtenerCliente(String identificacion){
        Cliente cliente = null;
        try {
            cliente = (Cliente) listaPersonas.stream().filter(p -> p.getIdentificacion().equals(identificacion)).findFirst().get();
        }
        catch(Exception e) {

        }
        return cliente;
    }

    /**
     * Metodo para crear un Cliente en Almacen
     * @param tipoCliente
     * @param nombre
     * @param apellido
     * @param identificacion
     * @param direccion
     * @param telefono
     * @param email
     * @param fechaNacimiento
     * @param nit
     * @return
     * @throws ObjectException
     */
    public String crearCliente(String nombre, String apellido, String identificacion, String direccion, String telefono, TipoCliente tipoCliente, String email, String fechaNacimiento, String nit) throws ObjectException {
        Cliente clienteExistente = obtenerCliente(identificacion);
        String mensaje = "";
        if(clienteExistente == null){
            if(tipoCliente.equals(TipoCliente.NATURAL)){
                listaPersonas.add(new ClienteNatural(nombre, apellido, identificacion, direccion,  telefono, tipoCliente, email, fechaNacimiento));
                mensaje = "El cliente fue registrado exitosamente";
            }
            if(tipoCliente.equals(TipoCliente.JURIDICO)){
                listaPersonas.add(new ClienteJuridico(nombre, apellido, identificacion, direccion, telefono, tipoCliente, nit));
                mensaje = "El cliente fue registrado exitosamente";
            }
        }
        else{
            throw new ObjectException("El cliente ya se encuentra registrado");
        }
        return mensaje;
    }

    /**
     * Metodo para actualizar un Cliente en Almacen
     * @param nombre
     * @param apellido
     * @param identificacion
     * @param direccion
     * @param telefono
     * @param email
     * @param fechaNacimiento
     * @param nit
     * @return
     * @throws ObjectException
     */
    public String actualizarCliente(String nombre, String apellido, String identificacion, String direccion, String telefono, String email, String fechaNacimiento, String nit) throws ObjectException {
        Cliente cliente = obtenerCliente(identificacion);
        String mensaje = "";
        if(cliente != null){
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            if(cliente.getTipoCliente().equals(TipoCliente.NATURAL)){
                assert cliente instanceof ClienteNatural;
                ClienteNatural cn = (ClienteNatural) cliente;
                cn.setEmail(email);
                cn.setFechaNacimiento(fechaNacimiento);
                mensaje = "Cliente actualizado exitosamente";
            }
           if(cliente.getTipoCliente().equals(TipoCliente.JURIDICO)){
                assert cliente instanceof ClienteJuridico;
                ClienteJuridico cj = (ClienteJuridico) cliente;
                cj.setNit(nit);
                mensaje = "Cliente actualizado exitosamente";
            }
        }
        else{
            throw new ObjectException("El cliente no se encuentra registrado");
        }
        return mensaje;
    }

    /**
     * Metodo para eliminar un Cliente de Almacen
     * @param identificacion
     * @return
     * @throws ObjectException
     */
    public String eliminarCliente(String identificacion) throws ObjectException {
        Cliente cliente = obtenerCliente(identificacion);
        String mensaje;
        if(cliente != null){
            listaPersonas.remove(cliente);
            mensaje = "El cliente fue eliminado exitosamente";
        }
        else{
            throw new ObjectException("El cliente no se encuentra registrado");
        }
        return mensaje;
    }

    /**
     * Metodo para obtener la lista de clientes del Almacen
     * @return
     */
    public List<Cliente> getListaClientes(){
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        listaPersonas.forEach(p -> {
            if(p instanceof Cliente){
                listaClientes.add((Cliente) p);
            }
        });
        return listaClientes;
    }

    //CRUD Producto
    /**
     * Metodo para obtener un Producto en Almacen
     * @param codigo
     * @return
     */
    public Producto obtenerProducto(String codigo){
        Producto producto = null;
        try {
            producto = listaProductos.stream().filter(p -> p.getCodigo().equals(codigo)).findFirst().get();
        }
        catch(Exception e) {

        }
        return producto;
    }

    /**
     * Metodo para crear un Producto en Almacen
     * @param tipoProducto
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param valorUnitario
     * @param cantidadInventario
     * @param fechaEnvasado
     * @param pesoEnvase
     * @param paisOrigen
     * @param fechaVencimiento
     * @param codigoAprobacion
     * @param temperaturaRecomendada
     * @return
     * @throws ObjectException
     */
    public String crearProducto(String codigo, String nombre, String descripcion, double valorUnitario, int cantidadInventario, TipoProducto tipoProducto, String fechaEnvasado, double pesoEnvase, PaisOrigen paisOrigen,
                                String fechaVencimiento, String codigoAprobacion, String temperaturaRecomendada) throws ObjectException {
        Producto producto = obtenerProducto(codigo);
        String mensaje = "";
        if(producto == null){
            if(tipoProducto.equals(TipoProducto.ENVASADO)){
                listaProductos.add(new ProductoEnvasado(codigo, nombre, descripcion, valorUnitario, cantidadInventario, tipoProducto, fechaEnvasado, pesoEnvase, paisOrigen));
                mensaje = "El producto fue registrado exitosamente";
            }
            if(tipoProducto.equals(TipoProducto.PERECEDERO)){
                listaProductos.add(new ProductoPerecedero(codigo, nombre, descripcion, valorUnitario, cantidadInventario, tipoProducto, fechaVencimiento));
                mensaje = "El producto fue registrado exitosamente";
            }
            if(tipoProducto.equals(TipoProducto.REFRIGERADO)){
                listaProductos.add(new ProductoRefrigerado(codigo, nombre, descripcion, valorUnitario, cantidadInventario, tipoProducto, codigoAprobacion, temperaturaRecomendada));
                mensaje = "El producto fue registrado exitosamente";
            }
        }
        else{
            throw new ObjectException("El producto ya se encuentra registrado");
        }
        return mensaje;
    }

    /**
     * Metodo para actualizar Producto en Almacen
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param valorUnitario
     * @param cantidadInventario
     * @param fechaEnvasado
     * @param pesoEnvase
     * @param paisOrigen
     * @param fechaVencimiento
     * @param codigoAprobacion
     * @param temperaturaRecomendada
     * @return
     * @throws ObjectException
     */
    public String actualizarProducto(String codigo, String nombre, String descripcion, double valorUnitario, int cantidadInventario, String fechaEnvasado, double pesoEnvase, PaisOrigen paisOrigen,
                                     String fechaVencimiento, String codigoAprobacion, String temperaturaRecomendada) throws ObjectException {
        Producto producto = obtenerProducto(codigo);
        String mensaje = "";
        if(producto != null){
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setValorUnitario(valorUnitario);
            producto.setCantidadInventario(cantidadInventario);
            if(producto.getTipoProducto().equals(TipoProducto.ENVASADO)){
                assert producto instanceof ProductoEnvasado;
                ProductoEnvasado pe = (ProductoEnvasado) producto;
                pe.setFechaEnvasado(fechaEnvasado);
                pe.setPesoEnvase(pesoEnvase);
                pe.setPaisOrigen(paisOrigen);
                mensaje = "Producto actualizado exitosamente";
            }
            if(producto.getTipoProducto().equals(TipoProducto.PERECEDERO)){
                assert producto instanceof ProductoPerecedero;
                ProductoPerecedero pp = (ProductoPerecedero) producto;
                pp.setFechaVencimiento(fechaVencimiento);
                mensaje = "Producto actualizado exitosamente";
            }
           if(producto.getTipoProducto().equals(TipoProducto.REFRIGERADO)){
               assert producto instanceof ProductoRefrigerado;
               ProductoRefrigerado pr = (ProductoRefrigerado) producto;
               pr.setCodigoAprobacion(codigoAprobacion);
               pr.setTemperaturaRecomendada(temperaturaRecomendada);
               mensaje = "Producto actualizado exitosamente";
           }
        }
        else{
            throw new ObjectException("El producto no se encuentra registrado");
        }
        return mensaje;
    }

    /**
     * Metodo para eliminar un Producto de Almacen
     * @param codigo
     * @return
     * @throws ObjectException
     */
    public String eliminarProducto(String codigo) throws ObjectException {
        Producto producto = obtenerProducto(codigo);
        String mensaje;
        if(producto != null){
            listaProductos.remove(producto);
            mensaje = "El producto fue eliminado exitosamente";
        }
        else{
            throw new ObjectException("El producto no se encuentra registrado");
        }
        return mensaje;
    }

    //Transacciones

    /**
     * Metodo para obtener una Transaccion en almacen
     * A aprtir del codigo de Transaccion
     * @param codigo
     * @return
     */
    public Transaccion obtenerTransaccion(String codigo){
        Transaccion transaccion = null;
        try {
            transaccion = listaTransacciones.stream().filter(t -> t.getCodigo().equals(codigo)).findFirst().get();
        }
        catch(Exception e) {

        }
        return transaccion;
    }

    /**
     * Metodo para realizar un Detalle de Transaccion en una Transaccion existente
     * Se verifica que la cantidad pedida se encuentre en inventario
     * @param codigoTransaccion
     * @param codigoProducto
     * @param cantidadProducto
     * @return
     * @throws ObjectException
     */
    public String realizarDetalleTransaccion(String codigoTransaccion, String codigoProducto, int cantidadProducto) throws ObjectException {
        Transaccion transaccion = obtenerTransaccion(codigoTransaccion); if(transaccion==null) throw new ObjectException("La transaccion no se encuentra registrada");
        Producto producto = obtenerProducto(codigoProducto); if(producto==null) throw new ObjectException("El producto no se encuentra registrado");
        String mensaje = "";
        //Se verifica que la cantidad pedida se encuentre en inventario
        if(producto.verificarExistencias(cantidadProducto)){
            double subtotal = cantidadProducto * producto.getValorUnitario();
            DetalleTransaccion detalle = new DetalleTransaccion(transaccion, producto, cantidadProducto, subtotal);

            //Se añade el DetalleTransaccion a la Transaccion
            transaccion.aniadirDetalleTransaccion(detalle);
            //Se modifica la informacion de la Transaccion
            transaccion.modificarTotal(subtotal);
            //Se modifica la cantidad de inventario del producto
            producto.setCantidadInventario(producto.getCantidadInventario()-cantidadProducto);

            mensaje = "El detalle de transaccion fue realizado exitosamente";
        }
        else{
            throw new ObjectException("La cantidad requerida excede la cantidad en inventario");
        }
        return mensaje;
    }

    /**
     * Metodo para crear una Transaccion en Almacen
     * @param codigo
     * @param fecha
     * @param identificacionCliente
     * @return
     * @throws ObjectException
     */
    public String realizarTransaccion(String codigo, String fecha, String identificacionCliente) throws ObjectException {
        Transaccion tExistente = obtenerTransaccion(codigo); if(tExistente!=null) throw new ObjectException("La transaccion ya se encuentra registrada");
        Cliente cliente = obtenerCliente(identificacionCliente); if(cliente == null) throw new ObjectException("El cliente no se encuentra registrado");

        Transaccion transaccion = new Transaccion(codigo, fecha, cliente);
        //Se añade la Transaccion a la lista de transacciones del Almacen
        listaTransacciones.add(transaccion);
        //Se añade la Transaccion a la lista de Transacciones del Cliente
        cliente.aniadirTransaccion(transaccion);

        return "Transaccion realizada correctamente";
    }

    /**
     * Metodo para eliminar una Transaccion en Almacen
     * Devuelve los produvtos de los Detalle de Transaccion que se realizaron en la Transaccion
     * @param codigo
     * @return
     * @throws ObjectException
     */
    public String eliminarTransaccion(String codigo) throws ObjectException {
        Transaccion t = obtenerTransaccion(codigo); if(t==null) throw new ObjectException("La transaccion no se encuentra registrada");
        t.devolverInventario();
        listaTransacciones.remove(t);
        return "La transaccion fue eliminada exitosamente";
    }

    /**
     * Metodo para devolver una lista con las identificaciones de los Cliente de Almacen
     * @return
     */
    public List<String> devolverIdentificacionesClientes(){
        List<String> listaIdentificaciones = new ArrayList<String>();
        getListaClientes().forEach(c -> listaIdentificaciones.add(c.getIdentificacion()));
        return listaIdentificaciones;
    }

    /**
     * Metodo para devolver una lista con los codigos de los productos de Almacen
     * @return
     */
    public List<String> devolverCodigosProductos(){
        List<String> listaProductos = new ArrayList<String>();
        getListaProductos().forEach(p -> listaProductos.add(p.getCodigo()));
        return listaProductos;
    }

}
