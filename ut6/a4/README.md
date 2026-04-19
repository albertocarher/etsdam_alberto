# UT6-A4 Diseño de tests para un gestor de carrito de compra (Java + Maven + JaCoCo)

### Contexto

Una tienda online está desarrollando un pequeño módulo en **Java** que gestiona el cálculo del importe de un carrito de compra.

El equipo de desarrollo ha implementado varias funciones, pero el equipo de QA (vosotros) debe diseñar los tests que verifiquen su correcto funcionamiento.

El objetivo de esta práctica es diseñar e implementar una batería completa de tests usando **JUnit 5** y comprobar la cobertura de código con **JaCoCo** integrado en Maven.



### Comportamiento del sistema

El módulo permite realizar las siguientes operaciones:

#### 1. Calcular el subtotal de un carrito

**Método:** `calcularSubtotal(List<Producto> carrito)`

El carrito es una lista de objetos `Producto`.

Cada producto contiene:
- `nombre` (String)
- `precio` (double)
- `cantidad` (int)

Ejemplo:

```java
List<Producto> carrito = List.of(
    new Producto("teclado", 30, 2),
    new Producto("raton", 10, 1)
);
```

El subtotal se calcula realizando la operación:

```
precio * cantidad
```

para cada producto y sumando los resultados.



#### 2. Aplicar descuento

**Método:** `aplicarDescuento(double subtotal, double descuento)`

El descuento es un porcentaje entre **0 y 100**.

Ejemplo:

```
subtotal = 100
descuento = 10

resultado = 90
```



#### 3. Calcular gastos de envío

**Método:** `calcularEnvio(double subtotal)`

- Si `subtotal >= 100` → envío gratis (0€)
- Si `subtotal < 100` → envío 5€



#### 4. Calcular total del pedido

**Método:** `calcularTotal(List<Producto> carrito, double descuento)`

El proceso que se debe cumplir es:

```
SUBTOTAL -> APLICAR DESCUENTO -> AÑADIR ENVÍO
```



### Trabajo a realizar

Debes diseñar una batería de tests utilizando **JUnit 5** que verifique el comportamiento del sistema.

Tus tests deben cubrir al menos los siguientes casos:

#### Subtotal
- carrito con varios productos
- carrito con un solo producto
- carrito vacío

#### Descuentos
- descuento 0%
- descuento válido
- descuento 100%
- descuento inválido (ej: negativo o mayor de 100)

#### Envío
- subtotal menor que 100
- subtotal mayor o igual que 100

#### Total del pedido
- pedido sin descuento
- pedido con descuento
- pedido con envío gratis



### Requisitos técnicos

- Proyecto gestionado con **Maven**
- Tests implementados con **JUnit 5**
- Estructura estándar:

```
src/main/java
src/test/java
```

- Debes crear una clase de test, por ejemplo:

```
CarritoTest.java
```

- Debes implementar **al menos 12 tests distintos**



### Cobertura de código con JaCoCo

Una vez implementados los tests debes analizar qué porcentaje del código está siendo ejecutado por las pruebas.

Para ello utilizaremos **JaCoCo**.

Ejecuta el siguiente comando en la terminal:

```
mvn clean test
```

Después abre el informe generado en:

```
target/site/jacoco/index.html
```

Incluye una **captura de pantalla** del informe de cobertura donde se vea:

- porcentaje de cobertura
- clases analizadas



### Análisis de errores detectados

Durante la ejecución de los tests es posible que algunos de ellos fallen. Esto puede indicar que el código contiene errores.

Responde a las siguientes preguntas en este documento:

#### 1. Tests que han fallado

- Indica qué tests han fallado durante la ejecución inicial
- Explica brevemente por qué esos tests deberían pasar según el comportamiento descrito

• subtotal_variosProductos — Obtenido 40.0 en lugar de 70.0
El método no multiplica precio × cantidad, suma solo el precio unitario. El carrito tiene (30×2 + 10×1 = 70) pero el código produce (30+10 = 40).

• envio_subtotalIgualA100 — Obtenido 5.0 en lugar de 0.0
El umbral usa > (mayor estricto) en lugar de >= (mayor o igual). Cuando el subtotal es exactamente 100€ debería ser gratis, pero la condición es falsa y cobra 5€.

• descuento_invalido_negativo — Obtenido 110.0 (precio aumentado)
El método no valida el rango del descuento. Un valor negativo como -10 produce un resultado superior al precio original, lo cual es un error de negocio.

• descuento_invalido_mayor100 — Obtenido -50.0 (precio negativo)
Un descuento del 150% produce un precio negativo (-50€), comportamiento claramente incorrecto que el método no detecta.

• total_sinDescuento_conEnvio — Obtenido 45.0 en lugar de 75.0
Consecuencia directa del error en calcularSubtotal: el subtotal se calcula mal, lo que arrastra el error al total.

• total_envioGratis — Obtenido 55.0 en lugar de 100.0
Doble impacto: el subtotal de 2×50€ se calcula como 50€ (falta ×cantidad), y como 50 < 100 se cobra envío de 5€, dando 55 en lugar de 100.



#### 2. Identificación de errores en el código

Si has detectado errores en el programa, indica:

- en qué método se encuentran
- qué línea del código es incorrecta
- por qué produce un resultado incorrecto

Error 1 – calcularSubtotal(): no multiplica por cantidad
'''java
Código incorrecto:
for (Producto p : carrito) {
    subtotal += p.getPrecio();  // ← ERROR: ignora la cantidad
}
''''
El precio unitario se suma directamente, ignorando cuántas unidades hay de cada producto. El subtotal siempre coincidirá con la suma de precios unitarios, independientemente de las cantidades.

Error 2 – calcularEnvio(): operador > en vez de >=
'''java
Código incorrecto:
if (subtotal > 100) {    // ← ERROR: debería ser >=
    return 0;
} else {
    return 5;
}
''''
Según las especificaciones, el envío es gratuito cuando subtotal >= 100. Con > (mayor estricto) el caso límite subtotal = 100 cobra 5€ incorrectamente.

Error 3 – calcularTotal(): error conceptual en el orden de cálculo
'''java
double subtotal     = calcularSubtotal(carrito);
double conDescuento = aplicarDescuento(subtotal, descuento);
double envio        = calcularEnvio(subtotal);  // ← ERROR conceptual
return conDescuento + envio;
El envío se calcula sobre el subtotal bruto (antes del descuento). Esto produce resultados incorrectos cuando el descuento reduce el importe por debajo de 100€: se aplica envío gratis aunque el cliente pague menos de 100€, o viceversa. El envío debería calcularse sobre el importe con descuento aplicado.
''''


#### 3. Corrección propuesta

Explica cómo se debería corregir el código para que el comportamiento sea el esperado.

Incluye el fragmento de código corregido.

Para arreglar los tests hariamos esto:
3.1 calcularSubtotal() — corregido
'''java
public double calcularSubtotal(List<Producto> carrito) {
    double subtotal = 0;
    for (Producto p : carrito) {
        subtotal += p.getPrecio() * p.getCantidad();  
    }
    return subtotal;
}
''''

3.2 calcularEnvio() — corregido
'''java
public double calcularEnvio(double subtotal) {
    if (subtotal >= 100) {  
        return 0;
    } else {
        return 5;
    }
}
''''

3.3 calcularTotal() — corregido (error conceptual)
'''java
public double calcularTotal(List<Producto> carrito, double descuento) {
    double subtotal     = calcularSubtotal(carrito);
    double conDescuento = aplicarDescuento(subtotal, descuento);
    double envio        = calcularEnvio(conDescuento);  // ✔ sobre precio con dto.
    return conDescuento + envio;
}
''''

3.4 aplicarDescuento() — validación de rango añadida
'''java
public double aplicarDescuento(double subtotal, double descuento) {
    if (descuento < 0 || descuento > 100) {
        throw new IllegalArgumentException(
            "Descuento inválido: " + descuento + ". Rango: 0-100");
    }
    return subtotal - (subtotal * descuento / 100);
}
''''


#### 4. Resultado final

Tras diseñar los tests y analizar el código:

- ¿cuántos tests has implementado?
Se han implementado 14 tests.

- ¿qué porcentaje de cobertura has obtenido?
Se ha obtenido un 100% de cobertura 

- ¿todos los tests pasan correctamente?
Han pasado 8 de los 14 tests.



### Entrega

Debes subir a tu repositorio de GitHub, en la carpeta correspondiente:

- Código fuente del proyecto
- Tests implementados
- Archivo `pom.xml`
- Captura de cobertura JaCoCo
- Documento con el análisis realizado
