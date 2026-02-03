Modela un **diagrama de secuencia UML** correspondiente al siguiente flujo funcional exacto del sistema. No se permite omitir, fusionar ni reinterpretar mensajes. Debes representar **todos los objetos, todos los mensajes síncronos, todos los mensajes de retorno y todas las condiciones descritas**, exactamente en el orden indicado.

**Contexto**

Un sistema web de venta de billetes de autobús implementa la operación `comprarBillete()`. El usuario final interactúa con el **NavegadorWeb**, el cual se comunica con un **ControladorCompra**, que delega en un **ServicioPagos** y en un **RepositorioBilletes**.

Los objetos involucrados —que deberán aparecer como líneas de vida— son exactamente los siguientes:

1. Usuario (actor)
2. NavegadorWeb
3. ControladorCompra
4. ServicioPagos
5. RepositorioBilletes

> No debe añadirse ningún otro objeto.

**Flujo secuencial exacto**

Cuando el **Usuario** pulsa el botón “Comprar”, ocurre la siguiente secuencia de manera estricta:

1. El Usuario envía al NavegadorWeb la acción `clicComprar()`.

2. El **NavegadorWeb** envía al **ControladorCompra** la petición síncrona `comprarBillete(dni, idViaje, tarjetaCredito)`.

3. El **ControladorCompra** envía al **ServicioPagos** el mensaje síncrono `autorizarPago(tarjetaCredito, importeViaje)`.

4. El **ServicioPagos** devuelve al **ControladorCompra** un mensaje de retorno con un valor booleano:

- `true` si el pago se autoriza correctamente.

- ``false si el pago es rechazado.

>El diagrama debe modelar únicamente el caso exitoso, es decir, cuando el valor de retorno es `true`. No debe incluir ramas alternativas.

5. Si `autorizarPago()` devuelve `true`, el **ControladorCompra** envía al **RepositorioBilletes** el mensaje síncrono `generarBillete(dni, idViaje)`.

6. El **RepositorioBilletes** devuelve al **ControladorCompra** un mensaje de retorno que contiene exactamente un objeto estructurado: `{codigoBillete, fechaEmision}`.

7. El **ControladorCompra** envía al **NavegadorWeb** el mensaje de retorno `resultadoCompra(objetoBillete)`, donde `objetoBillete` es el mismo objeto recibido desde el repositorio.

8. El **NavegadorWeb** envía al **Usuario** el mensaje `mostrarConfirmacion(codigoBillete)`.

**Restricciones obligatorias**

- Todos los mensajes deben ser síncronos (flecha con punta llena).

- Todos los retornos deben representarse explícitamente (flecha discontinua).

- No debe modelarse tratamiento de errores, excepciones, ni condiciones alternativas.

- El orden de los mensajes debe ser exactamente el descrito.

- No debe haber paralelismo ni ejecución concurrente.

- No se deben añadir mensajes adicionales.

A continuación se muestra el diagrama de secuencias:

![Diagrama de secuencias](./img/001.png)



