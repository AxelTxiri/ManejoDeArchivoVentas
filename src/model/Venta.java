package model;

/**
 * Clase que representa una venta.
 * 
 * Contiene la información básica de una venta:
 * - producto
 * - cantidad
 * - precio unitario
 */
public class Venta {

    // Nombre del producto vendido
    private String producto;

    // Cantidad de unidades vendidas
    private int cantidad;

    // Precio por unidad del producto
    private double precioUnitario;

    /**
     * Constructor de la clase Venta.
     * 
     * Flujo:
     * - Recibe los datos de la venta
     * - Asigna los valores a los atributos del objeto
     * 
     * @param producto       nombre del producto
     * @param cantidad       cantidad vendida
     * @param precioUnitario precio por unidad
     */
    public Venta(String producto, int cantidad, double precioUnitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return nombre del producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * Modifica el nombre del producto.
     * 
     * @param producto nuevo nombre del producto
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad vendida.
     * 
     * @return cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Modifica la cantidad vendida.
     * 
     * @param cantidad nueva cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario.
     * 
     * @return precio por unidad
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Modifica el precio unitario.
     * 
     * @param precioUnitario nuevo precio
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Calcula el total de la venta.
     * 
     * Flujo:
     * - Multiplica la cantidad por el precio unitario
     * 
     * @return total de la venta
     */
    public double calcularTotal() {
        return cantidad * precioUnitario;
    }
}