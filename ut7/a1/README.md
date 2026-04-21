> Alberto Carballo Hernández y Nauzet Torres Tejera

# UT7-A1 – Identificación de “malos olores” en el código


### Objetivo de la práctica

El objetivo de esta práctica es **analizar un programa que funciona correctamente pero cuya calidad de código es mejorable**.

Durante la sesión se realizará un **análisis colectivo del código para detectar “malos olores” (code smells)**. Estos son indicios de que el diseño del programa podría mejorarse mediante técnicas de refactorización.

- En esta práctica **no se debe modificar el código**.  
- El trabajo consiste únicamente en **analizarlo y debatir sobre su calidad**.


### Instrucciones de la práctica

Se proporciona un programa sencillo que simula un **sistema básico de gestión de pedidos**.

El programa realiza varias operaciones:

- almacena una lista de productos
- calcula el precio de cada producto en función de su cantidad
- aplica un descuento en algunos casos
- calcula el coste de envío
- calcula el total del pedido

Aunque el programa funciona correctamente, su código presenta **diversos problemas de diseño y calidad**.

El objetivo de la práctica es **analizar el código para identificar esos problemas**.

### Trabajo en clase

1. Lee el código detenidamente.
2. Analiza cómo está organizado.
3. Detecta posibles problemas de diseño o calidad del código.
4. Anota todos los problemas que encuentres.

Durante la sesión se realizará un **debate en clase**, donde cada grupo expondrá los problemas detectados.

### Preguntas que pueden ayudarte en el análisis

Para identificar posibles problemas puedes plantearte las siguientes cuestiones:

- ¿Hay **métodos demasiado largos**?
- ¿Las **variables tienen nombres claros y descriptivos**?
- ¿Se repite código en diferentes partes del programa?
- ¿Hay **números que aparecen directamente en el código sin explicación**?
- ¿El código mezcla distintas responsabilidades?
- ¿El programa sería fácil de modificar o ampliar?
- ¿Falta documentación o comentarios que expliquen el funcionamiento?

### Entregable

Cada grupo deberá entregar un documento que incluya:

- una lista de los problemas detectados en el código
- una breve explicación de cada problema
- una posible mejora para cada caso
###### Nuestra reacción al ver este código
![](./img/xd.webp)
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


### Código a analizar

El código a analizar es el siguiente:

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
> Alberto Carballo Hernández y Nauzet Torres Tejera