package UT7A2.src;
import java.util.List;

/**
 * CLASE PEDIDO (MEJORA LA LÓGICA)
 */
public class Pedido {

    private List<Producto> productos;

    private static final double descuento = 0.10;
    private static final int maximoDescuento = 2;
    private static final double precioEnvio = 5;
    private static final double minimoParaEnvioGratis = 100;

    public Pedido(List<Producto> productos) {
        this.productos = productos;
    }

/**
 * PROCESA EL PEDIDO
 * @return TOTAL DEL PEDIDO
 */
    public double procesarPedido() {
        double total = 0;

        for (Producto producto : productos) {

            double subtotal = producto.calcularSubtotal(descuento, maximoDescuento);

            mostrarProducto(producto, subtotal);

            subtotal = sumarEnvio(subtotal);

            total += subtotal;

            imprimirSeparador();
        }

        return total;
    }
/**
 * MUESTRA CADA PRODUCTO
 * @param producto EL PRODUCTO
 * @param subtotal EL SUBTOTAL
 */
    private void mostrarProducto(Producto producto, double subtotal) {
        System.out.println(
                "Producto: " + producto.getNombre() + "\n" +
                        "Precio: " + producto.getPrecio() + "\n" +
                        "Cantidad: " + producto.getCantidad() + "\n" +
                        "Subtotal: " + subtotal);
    }
/**
 * SUMA DEL ENVIO
 * @param subtotal SUBTOTAL
 * @return SUBTOTAL
 */
    private double sumarEnvio(double subtotal) {
        if (subtotal > minimoParaEnvioGratis) {
            System.out.println("Envio gratis");
        } else {
            System.out.println("Envio: " + precioEnvio + "€");
            subtotal += precioEnvio;
        }
        return subtotal;
    }
/**
 * IMPRIME UN SEPARADOR
 */
    private void imprimirSeparador() {
        System.out.println("-------------------");
    }
}