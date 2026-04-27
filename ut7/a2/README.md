> Alberto Carballo Hernández & Nauzet Torres Tejera

# Unidad 7 Actividad 2
## Refactorización del código

### Objetivo de la práctica

El objetivo de esta práctica es mejorar la calidad del código analizado en la práctica anterior aplicando técnicas de refactorización.

En esta práctica se debe corregir esos problemas, manteniendo en todo momento el comportamiento original del programa.

El objetivo es transformar el código para que sea:
+ Más claro
+ Más organizado
+ Más fácil de mantener
+ Más fácil de ampliar


---

#### Código original (Con code smells encontrados y marcados)
```java

// El código mezcla respnsabilidades como cálculos o lógica de negocios con impresiones en pantalla "println".
// Dividir la lógica en clases separadas
// Falta documentación en el programa, por lo que seria dificil entender, modificar o ampliar el programa.
// Poca flexibilidad debido a datos fijos como "total > 500".
// Sin programación defensiva.

import java.util.ArrayList;

class Producto { // Sin encapsulación y falta de métodos.

    String n;   // Nombre de variable poco claro o descriptivo
    double p;   // Nombre de variable poco claro o descriptivo
    int c;      // Nombre de variable poco claro o descriptivo

    public Producto(String n, double p, int c) {
        this.n = n;
        this.p = p;
        this.c = c;
    }
}

public class Tienda { 

    public static void main(String[] args) { // Método main demasiado largo

        // Nombre de lista poco claro o descriptivo
        // No queda claro si la lista es lista de compras (carrito) o lista de productos de la tienda.
        // Debería ir en otra clase

        ArrayList<Producto> lista = new ArrayList<>(); 

        lista.add(new Producto("Teclado", 30, 2));
        lista.add(new Producto("Raton", 15, 3));
        lista.add(new Producto("Monitor", 200, 1));

        double total = 0; // Nombre de variable poco claro o descriptivo

        for (int i = 0; i < lista.size(); i++) {

            Producto p = lista.get(i); // Nombre de variable poco claro o descriptivo || Confundible con variable p dentro de Producto

            double subtotal = p.p * p.c; // Nombre de variable poco claro o descriptivo || Deberia ser un método en producto

            if (p.c > 2) { // Número no explicado
                subtotal = subtotal - (subtotal * 0.1); // Número no explicado
            }

            // Código duplicado (Se puede imprimir todo eso con un único println)

            System.out.println("Producto: " + p.n);
            System.out.println("Precio: " + p.p);
            System.out.println("Cantidad: " + p.c);
            System.out.println("Subtotal: " + subtotal);

            // Calcular el envío deberia ser un método en producto
            // Cálculo de envío dentro de un bucle, poco eficiente.

            if (subtotal > 100) { // Número no explicado
                System.out.println("Envio gratis");
            } else {
                System.out.println("Envio: 5 euros");
                subtotal = subtotal + 5; // Número no explicado
            }

            total = total + subtotal; // total += subtotal;

            System.out.println("-------------------");
        }

        System.out.println("TOTAL PEDIDO: " + total);

        if (total > 500) { // Número no explicado
            System.out.println("Cliente VIP");
        }

    }
}
```
---
> ##### Lista de problemas que hemos detectado:
>> * Nombre de variable poco claro o descriptivo
>>      * Renombrar las variables, métodos y elementos de forma que sea entendible para aquel que lo lea.
>> * Método main demasiado largo
>>      * Creación de otros métodos o clases especificas para optimizar la lógica de negocio y responsabilidades.
>> * Números no explicados
>>      * Existen números sin explicación ni coherencia en el código, por lo que habría que documentar estos o convertirlos a variable o atributo.
>> * Código duplicado
>>      * Existen diversos códigos que se pueden realizar como un único código.
>> * El código mezcla responsabilidades
>>      * El main está realizando casi todo el programa, por lo que se deberia separar en diferentes clases o métodos.
>> * Falta documentación en el programa
>>      * Documentar cada parte de nuestro programa para mejor entendimiento del programa y facilitar mejoras, actualizaciones o errores.
>> * Sin programación defensiva
>>      * No hay código que proteja de posibles entradas nulas, listas vacias, etc, por lo que se deberá realizar este código para evitar errores futuros.
>> * Poca flexibilidad debido a datos fijos
>>      * Al haber datos fijos, una actualización en un descuento por ejemplo, tendrimos que ir cambiandolo en todo el codigo, al convertir estos números en variables, con tan solo cambiarlo en un único lugar evitariamos este problema a futuro cuando nuestro código sea demasiado grande.
---

### Trabajo realizado

*   En primer lugar, tras haber revisado el código original comenzamos por separar el contenido en 3 clases diferentes, una para la tienda que contiene el main, otra para productos y otra para pedidos.

*   Durante la creación de estas clases, los nombres de variables se reescribieron para que fueran claros y detallados, a su vez, según se iba construyendo el proyecto, tambien se realizaba la documentación necesaria para el buen entendimiento del programa.

*   Antes que nada, se realizó programación defensiva para evitar posibles errores y problemas.

*   Comenzando por la creación de la clase Producto, con sus variables correspondientes, métodos como getters, y un método para calcular el descuento.

*   Siguiendo con la clase Pedido, se definieron las variables descuento, maximoDescuento, precioEnvio y minimoParaEnvioGratis y una lista de productos, eliminando así los números mágicos que aparecian sin explicación, facilitando a su vez la posible actualización de estas cifras.

*   En esta clase, se crearon los siguientes métodos:
    *   procesarPedidos -->     Recorre la lista de productos calculando el precio total del pedido.

    *   mostrarProductos -->    Imprime los productos con su información correspondiente del pedido, en la versión anterior se utilizaban varios println; en este caso, se utiliza uno solo para exactamente lo mismo.

    *   sumarEnvio -->  Suma el envio si el pedido no llega al mínimo para envío gratis.

    *   imprimirSeparador -->   Imprime un separador.

*   Para terminar se creó la clase Tienda, la cual contiene el método main del programa, donde se definió otro de los números mágicos, en este caso gastoMinimoVip, y se crearon los siguientes métodos:
    *   main    -->     Método main

    *   crearCarrito    -->     Lista donde se añaden los productos al carrito

    *   mostrarTotal    -->     Muestra el total calculado de los productos del pedido. 

    *   verificarClienteVip     --> Verifica si se alcanza el mínimo requerido para considerar al cliente VIP.



---

> [![PEDIDO](./img/carrito.png)](./UT7A2/src/Pedido.java)

> [![PRODUCTO](./img/producto.png)](./UT7A2/src/Producto.java)

> [![TIENDA](./img/tienda.png)](./UT7A2/src/Tienda.java)

