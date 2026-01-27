Elabora el diagrama de casos de uso que para el diseño de una aplicación que gestione los tramites a realizar en una clínica veterinaria en base a las siguientes premisas:


- La clínica veterinaria almacena datos de contacto de todos sus clientes como pueden ser: nombre, apellidos, DNI, fecha de nacimiento, teléfono o email. Estos datos son introducidos y gestionados por los auxiliares, que ejercen las funciones
administrativas. Para ello es necesario que el auxiliar se identifique en la aplicación.

- Además se almacena información de cada uno de las mascotas de las que es dueño cada cliente. Obviamente, cada cliente puede tener más de una mascota, pero cada mascota solo puede pertenecer a un único cliente. Se permite, además, cambiar el
dueño de una mascota por otro.Para ello es necesario que el auxiliar se identifique en la aplicación.
- Al dar de alta un nuevo animal, se comprobará en el registro del REIAC (Red Española de Identificación de Animales de Compañía) si el animal está correctamente dado de alta. Este proceso únicamente se hará en animales que tengan la obligación
de estar identificados.
- Cada vez que un veterinario realiza una consulta sobre un animal, esta queda almacenada incluyendo datos básicos como: tiempo de consulta, identificación de la persona que lo ha tratado, animal tratado, importe total, resolución, recetas… Para
calcular el tiempo de la consulta el veterinario tendrá un botón en la aplicación donde pueda pulsar cuando comienza la consulta para calcular el tiempo a modo de cronómetro y otro botón para finalizar. Para iniciar la consulta es necesario que el veterinario se identifique en la aplicación.

- En caso de que el animal se quede ingresado en la clínica, el cliente debe ser capaz de acceder al estado en tiempo real del animal. Además podrá comunicarse con una cámara que tendrá el animal colocada, donde podrá ver su situación actual. La gestión
de estas cámaras no corresponde al sistema, sino que se utilizará una aplicación ya presente en el veterinario. Las recetas y otros documentos relacionados con el servicio se incluirán en un gestor de contenidos que ya está en funcionamiento en la clínica
veterinaria.
- Una vez terminado el servicio, el cliente no tiene porque realizar inmediatamente el pago, sino que puede identificarse posteriormente en la aplicación vía web y realizar el pago. Si el cliente tarda más de una semana se efectuará un recargo sobre el precio
inicial.
- Además, el cliente debe ser capaz de obtener un histórico de todas las consultas que ha recibido cualquiera de sus mascota
- Tanto para realizar el pago, obtener el histórico y ver el estado de la mascota ingresada es necesario que el cliente se identifique en la aplicación.. 


![Diagrama de casos de uso](/img/001.PNG)



