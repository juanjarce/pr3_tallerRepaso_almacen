package co.uniquindio.almacen.model;


public interface AdministradorDeTransacciones {

    void aniadirDetalleTransaccion(DetalleTransaccion detalleTransaccion);

    void devolverInventario();
}
