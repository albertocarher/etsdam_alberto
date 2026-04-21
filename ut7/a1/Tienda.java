import java.util.ArrayList;

class Producto {

    String n; // Nombre de variable poco claro o descriptivo
    double p; // Nombre de variable poco claro o descriptivo
    int c; // Nombre de variable poco claro o descriptivo

    public Producto(String n, double p, int c) {
        this.n = n;
        this.p = p;
        this.c = c;
    }
}

public class Tienda {

    public static void main(String[] args) {

        ArrayList<Producto> lista = new ArrayList<>();

        lista.add(new Producto("Teclado", 30, 2));
        lista.add(new Producto("Raton", 15, 3));
        lista.add(new Producto("Monitor", 200, 1));

        double total = 0;

        for (int i = 0; i < lista.size(); i++) {

            Producto p = lista.get(i);

            double subtotal = p.p * p.c;

            if (p.c > 2) {
                subtotal = subtotal - (subtotal * 0.1);
            }

            System.out.println("Producto: " + p.n);
            System.out.println("Precio: " + p.p);
            System.out.println("Cantidad: " + p.c);
            System.out.println("Subtotal: " + subtotal);

            if (subtotal > 100) {
                System.out.println("Envio gratis");
            } else {
                System.out.println("Envio: 5 euros");
                subtotal = subtotal + 5;
            }

            total = total + subtotal;

            System.out.println("-------------------");
        }

        System.out.println("TOTAL PEDIDO: " + total);

        if (total > 500) {
            System.out.println("Cliente VIP");
        }

    }
}