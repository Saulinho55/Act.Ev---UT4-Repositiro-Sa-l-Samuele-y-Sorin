Act.Ev- UT4: Saúl, Samuele y Sorin

En este proyecto, encontrarás un Menú de un Gestor de Hoteles. Donde podrás reservar, eliminar reserva, consultar y buscar las habitaciones (números, tipo o estado), añadir y eliminar clientes, buscar reservas activas, un resumen de los clientes y consultar también sus historiales.

Este proyecto cuenta con una estructura MVC donde se encuentran:
-3 modelos: Habitación, Cliente y Reserva. Además una carpeta llamada "excepciones", para crear las excepciones: ClienteNoEncontradoException y ReservaNoDisponibleException.
-App. Donde se encuentra Main: Donde se crea las habitaciones: SUITE, DOBLE y INDIVIDUAL y 3 ejemplos de usuarios. 
-View, se encuentra Pantalla. Donde se encuentra el switch para que el usuario pueda elegir la opción que desee entre: Salir, reservar habitación, cancelar reserva, consultar habitaciones, historial de clientes, resumen de clientes, buscar reservas activas, buscar habitación (Tipo, Estado, Número), añadir cliente y eliminar cliente.
-3 Controladores: ClienteController, ReservaController y HabitacionController. Donde se encuentran los métodos para todas las opciones y funcionalidades.

Para usar, se debe ejecutar el Main.java. Está localizado en la carpeta App. 
Al ejecutarse, se mostrará un menú con opciones del 0 al 11. Si se ejecuta otro número se ejecutará un error.
Si ejecuta el 0 (Salir). Se saldrá del programa.
Si ejecuta el 1 (Añadir Reserva). Deberá ingresar su ID, habitación y fecha de Check-In y Check-on. No puede superar 90 días, no puede reservar si tiene 3 reservas activas y no puede poner fechas anteriores o el check-on antes que el check-in.
Si ejecuta el 2 (Cancelar Reserva). Deberá ingresar su ID de su Reserva asociada y se cancelará. 
Si ejecuta el 3 (Consultar Habitaciones). Se mostrará la lista de las habitaciones y estados.
Si ejecuta el 4 (Historial Clientes). Se le pedirá el ID de Cliente. Al escribirla, se le mostrará sus reservas. 
Si ejecuta el 5 (Resumen Clientes). Se mostrará la lista de los clientes actuales.
Si ejecuta el 6 (Buscar reservas activas). Se le pedirá el ID de Cliente. Al escribirla, se le mostrará sus reservas activas. 
Si ejecuta el 7 (Buscar habitación Tipo). Se le pedirá poner el tipo y se le mostrará las habitaciones por ese tipo.
Si ejecuta el 8 (Buscar habitación Estado). Se le pedirá poner el estado y se le mostrará las habitaciones por ese estado.
Si ejecuta el 9 (Buscar habitación Número). Se le pedirá poner el número y se le mostrará las habitaciones por ese número.
Si ejecuta el 10 (Añadir Cliente). Se pedirá el nombre y el ID (Número de Teléfono). Si el número no tiene 9 caracteres, se mostrará un error. Si el cliente existe, se saltara un error.
Si ejecuta el 11 (Eliminar Cliente). Se pedirá el ID (Número de Teléfono). Si el cliente no existe, se saltara un error. Y si el ID no corresponde, saltará un error.

Todas las habitaciones están disponibles. 
Desde la 101 a 105 : Individuales
Desde la 201 a 205 : Dobles
Desde la 301 a 305 : Suites

Existe los clientes: 
Saúl Espino: 678324523
Fernando Alonso: 603237684
Alberto Moleiro: 659087546




