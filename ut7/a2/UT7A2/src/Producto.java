package UT7A2.src;

/**
 * CLASE PRODUCTO
 */
public class Producto {

    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }
/**
 * CALCULAR DESCUENTO
 * @param descuento EL DESCUENTO
 * @param limiteDescuento EL DESCUENTO MAXIMO
 * @return SUBTOTAL
 */
    public double calcularSubtotal(double descuento, int limiteDescuento) {
        double subtotal = precio * cantidad;

        if (cantidad > limiteDescuento) {
            subtotal -= subtotal * descuento;
        }

        return subtotal;
    }
}