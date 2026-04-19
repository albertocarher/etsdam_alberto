

import java.util.List;

public class CarritoService {

    public double calcularSubtotal(List<Producto> carrito) {
        double subtotal = 0;

        for (Producto p : carrito) {
            subtotal += p.getPrecio(); 
        }

        return subtotal;
    }

    public double aplicarDescuento(double subtotal, double descuento) {
        return subtotal - (subtotal * descuento / 100);
    }

    public double calcularEnvio(double subtotal) {
        if (subtotal > 100) { 
            return 0;
        } else {
            return 5;
        }
    }

    public double calcularTotal(List<Producto> carrito, double descuento) {
        double subtotal = calcularSubtotal(carrito);
        double conDescuento = aplicarDescuento(subtotal, descuento);
        double envio = calcularEnvio(subtotal); 

        return conDescuento + envio;
    }
}
