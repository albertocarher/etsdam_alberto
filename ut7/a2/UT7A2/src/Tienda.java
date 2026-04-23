package UT7A2.src;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASE TIENDA
 */
public class Tienda {

    private static final double gastoMinimoVip = 500;
/**
 * MAIN
 * @param args
 */
    public static void main(String[] args) {

        List<Producto> carrito = crearCarrito();

        if (carrito == null || carrito.isEmpty()) {
            throw new IllegalStateException("No hay productos en el carrito.");
        }

        Pedido pedido = new Pedido(carrito);

        double totalPedido = pedido.procesarPedido();

        mostrarTotal(totalPedido);
        verificarClienteVIP(totalPedido);
    }
/**
 * CARRITO DE LA COMPRA (LISTA)
 * @return LISTA
 */
    private static List<Producto> crearCarrito() {
        List<Producto> carrito = new ArrayList<>();

        carrito.add(new Producto("Teclado", 30, 2));
        carrito.add(new Producto("Raton", 15, 3));
        carrito.add(new Producto("Monitor", 200, 1));

        return carrito;
    }
/**
 * MUESTRA EL TOTAL
 * @param total EL TOTAL DE DINERO
 */
    private static void mostrarTotal(double total) {
        System.out.println("TOTAL PEDIDO: " + total);
    }
/**
 * VERIFICACION DE CLIENTE VIP
 * @param total EL TOTAL DE DINERO
 */
    private static void verificarClienteVIP(double total) {
        if (total > gastoMinimoVip) {
            System.out.println("Cliente VIP");
        }
    }
}