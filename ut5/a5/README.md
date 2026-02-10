Modela un **diagrama de secuencia UML** correspondiente al siguiente **flujo funcional exacto** del sistema. **No se permite omitir, fusionar ni reinterpretar mensajes**. Debes representar **todos los objetos**, **todos los mensajes síncronos**, **todos los mensajes de retorno** y **todas las condiciones descritas**, exactamente **en el orden indicado**.


**Contexto**

Un sistema domótico permite al usuario activar la calefacción de su vivienda mediante la operación `activarCalefaccion()`.  
El usuario final interactúa con una **AplicacionMovil**, que se comunica con un **ControladorDomotico**, el cual delega en un **ServicioClima** y en un **RepositorioEventos**.

Los objetos involucrados —que **deberán aparecer como líneas de vida**— son **exactamente** los siguientes:

- Usuario  
- AplicacionMovil  
- ControladorDomotico  
- ServicioClima  
- RepositorioEventos  

**No debe añadirse ningún otro objeto.**



**Flujo secuencial exacto**

Cuando el **usuario** pulsa el botón “Activar calefacción”, ocurre la siguiente secuencia estricta:

1. El **Usuario** envía a la **AplicacionMovil** la acción `clicActivarCalefaccion()`.

2. La **AplicacionMovil** envía al **ControladorDomotico** la petición síncrona  
   `activarCalefaccion(idVivienda, temperaturaObjetivo)`.

3. El **ControladorDomotico** envía al **ServicioClima** el mensaje síncrono  
   `encenderSistema(idVivienda, temperaturaObjetivo)`.

4. El **ServicioClima** devuelve al **ControladorDomotico** un mensaje de retorno** con un valor booleano:

   - `true` si el sistema se enciende correctamente.  
   - `false` si la activación falla.

   El diagrama debe modelar **únicamente el caso exitoso**, es decir, cuando el valor de retorno es **true**.
     
   **No debe incluir ramas alternativas.**

5. Si `encenderSistema()` devuelve `true`, el **ControladorDomotico** envía al **RepositorioEventos** el mensaje síncrono `registrarEvento(idVivienda, "calefaccion_activada")`.

6. El **RepositorioEvento**s devuelve al **ControladorDomotico** un mensaje de retorno que contiene exactamente un objeto estructurado:  `{idEvento, fechaRegistro}`.

7. El **ControladorDomotic**o envía a la **AplicacionMovil** el mensaje de retorno  
   `resultadoActivacion(objetoEvento)`, donde *objetoEvento* es el mismo objeto recibido desde el repositorio.

8. La **AplicacionMovil** envía al **Usuario** el mensaje  
   `mostrarConfirmacion(idEvento)`.


**Restricciones obligatorias**

- Todos los mensajes deben ser **síncronos** (flecha con punta llena).  
- Todos los **retornos** deben representarse explícitamente (flecha discontinua).  
- No debe modelarse tratamiento de errores, excepciones ni condiciones alternativas.  
- El **orden de los mensajes** debe ser exactamente el descrito.  
- No debe haber **paralelismo** ni ejecución concurrente.  
- **No se deben añadir mensajes adicionales.**



A continuación se muestra el diagrama de secuencias:

![](/img/001.png) 
