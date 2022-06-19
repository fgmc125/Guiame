# Guiame
<Trabajo integrador Teórico Práctico>

En el metaverso existen ciudades totalmente amuralladas donde la vida transcurre muros adentro, y no existe conexión con el exterior. Estas ciudades tienen forma rectangular, y el sentido de circulación de las calles que dan contra el muro, permiten dar una vuelta completa a la ciudad. Tanto estas calles que son las más externas como las internas, tienen un sentido único de circulación, que se alterna entre cada paralela, o sea que es necesario que tanto las calles nortesur, como las este-oeste, tienen que ser múltiplos de 2. Así es que podríamos construir ciudades de tamaño 6x8, medido en calles, o 100x80, pero nunca una de tamaño 5x5. Cada calle tiene su nombre y cada casa su numeración, siguiendo las reglas ordinarias de toda ciudad. El extremo superior izquierdo corresponde al inicio de la numeración. Todas las cuadras tienen 100 metros de largo, y las calles tienen 5 metros de ancho, y son de un solo carril. Todas las casas tienen tamaño 10 de frente, por lo que hay 10 casas por cuadra.

Primera parte del trabajo: Una persona, que se encuentra en un domicilio determinado al azar, decide llamar un taxi, que también se encuentra en una dirección al azar. Usted deberá mostrar en forma gráfica, la simulación del movimiento del vehículo desde el origen hasta el destino. La simulación mostrará que ocurre en cada segundo de tiempo. Para ello deberá incluir en un informe lo siguiente:

  1) Identificación de los objetos del problema.
  2) Diseño de las clases que correspondan.
  3) Determinar la relación entre las clases.
  4) Código de los métodos más importantes.
  5) Enumerar los conceptos de la teoría de POO usados en todo el trabajo.

El programa que debe construir recibirá como parámetro el tamaño de la ciudad. Luego se creará la ciudad y se asignará al azar tanto la ubicación del auto como del pasajero que lo llamó. Inmediatamente deberíamos ver, en tiempo real, el movimiento del vehículo hasta llegar al pasajero. Para ello, consideraremos lo siguiente:

  - El auto mide 5 metros de largo.
  - La velocidad del auto es de 5 metros por segundo - La aceleración de 0 a 5 es instantánea.
  - El frenado de 5 a 0 es instantáneo.
  - El giro, en cualquier sentido es instantáneo. El vehículo deberá mostrar claramente cuál es su parte delantera y su parte trasera, de forma gráfica.

Segunda parte del trabajo: Al problema anterior se agregará un dato más, que es el número de vehículos que circularán por la ciudad. Tendremos en cuenta que cada vehículo se ubicará también al azar, en un lugar vacío. También habrá un número igual de pasajeros y la ejecución del programa terminará cuando cada auto haya llegado a la posición de cada pasajero. Se deberá tener en cuenta, para evitar accidentes, que en las esquinas, los vehículos que vienen por la derecha tienen prioridad.
