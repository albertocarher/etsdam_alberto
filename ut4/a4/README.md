

La empresa **“AutoRent”**, dedicada al alquiler de automóviles, desea modelar su sistema de gestión de clientes, reservas, vehículos y agencias. El objetivo es construir un **diagrama de clases UML**, basado exclusivamente en la información siguiente.  

>El modelo final deberá incluir únicamente las clases indicadas y respetar estrictamente los atributos, cardinalidades y métodos especificados.

**Clientes**

La empresa gestiona **Clientes**, los cuales pueden efectuar reservas de **vehículos**. De cada **Cliente** debe almacenarse:

- `idCliente` (int, identificador único dentro de la empresa)
- `dni` (string)
- `nombre` (string)
- `direccion` (string)
- `telefono` (string)

Un cliente puede estar **avalado** por otro cliente de la empresa.  
Un cliente puede tener cero, una o varias reservas activas o históricas.


**Vehículos**

La empresa dispone de **Coches**, cada uno con:

- `matricula` (string)
- `marca` (string)
- `modelo` (string)
- `color` (string)
- `cocheDisponible` (boolean)

Cada Coche está **asignado permanentemente a un único Garaje**, y esta asignación **no puede cambiar**.  
Un Garaje puede almacenar varios coches.

**Garaje**

- `codigoGaraje`(string)
- `cantidadMaxCoches` (int) 

**Agencias**

Las reservas se gestionan a través de **Agencias**, cada una responsable de registrar reservas. (El enunciado no define atributos internos, por lo que la clase tendrá solo su identificador obligatorio):


- `nombre` (string)
- `direccion`(string)

Una Reserva se realiza **en exactamente una Agencia**.



**Reservas**

Cada **Reserva** representa el alquiler de uno o varios coches por parte de un Cliente. Se deben registrar los siguientes atributos:

- `idReserva` (int)
- `fechaInicio` (fecha)
- `fechaFin` (fecha)
- `precioTotal` (float)
- `entregado` (booleano → indica si los coches han sido devueltos)
- `litrosGasolinaIniciales` (float → litros existentes al crear la reserva)

Cada Reserva:

- es realizada por **un único Cliente**;
- puede involucrar **uno o varios Coches**;
- se registra en **una única Agencia**.

Además, para cada coche incluido en la reserva debe conservarse el **precio individual de alquiler** de dicho coche.  
(Este requisito implica una relación con atributo entre Reserva y Coche.)


Para cada una de las clases se deben implementar los siguientes **métodos**:

- Cliente
    - `listarReservas()`
    - `definirAval()`

- Coche
    - `obtenerDatos()`
    - `estaDisponible()`

- Garaje
    - `listarCoches()`

- Agencia
    - `registrarReserva()`
    - `listarReservas()`

- Reserva
    - `agregarCoche()`
    - `listarCoches()`
    - `calcularPrecioTotal()`
    - `marcarComoEntregado()`

A continuación se muestra el diagrama de clases creado:

![](/img/001.png)