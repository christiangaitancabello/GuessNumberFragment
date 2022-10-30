# GuessNumber

## Para que sirve
Este proyecto es un juego en el que el usuario es desafíado a adivinar un número aleatorio entre 1 y 100.

## Estructura del proyecto
El juego cuenta con tres Fragments:
+ ConfigFragment: Se le pide al jugador un nombre de usuario y el número de intentos máximos con los que contará para adivinar el número (Los datos son guardados en un POJO 'User' que se comparte con el siguiente Fragment).
+ PlayFragment: En este Fragment es donde transcurre el juego. El usuario trata de averiguar el número mientras recibe pistas de si el número es menor o mayor. Esta clase cuenta con otro POJO 'Game' donde se guarda:
  + Número a adivinar.
  + Número de intentos que lleva el usuario.
  + Booleano que refleja si el usuario ha salido victorioso del juego o no.
Se pasa al siguiente Fragment cuando el jugador adivine el número o se quede sin intentos.
+ EndPlayFragment: Este Fragment muestra el texto "VICTORIA" y el número de intento consumidos hasta el acierto por el jugador en caso de ganar, y "DERROTA" más la revelación del número que tenía que ser averiguado en caso de que el usuario fuese incapaz de acertarlo.

## Innovación con respecto a GuessNumber
### About Us
Además, también cuenta con un AboutUsFragment, donde se muestra un poco de información acerca del creador.

<p align="center">
<img src="https://user-images.githubusercontent.com/113918779/198888095-bbf8ddcc-39db-45dc-b297-036849548626.jpg" height="450" width="220" >
</p>

### Componente Navigation
Es un componente que se encarga de programar las transacciones entre fragments, para su uso se han añadido las siguientes dependencias:
      
      implementation 'androidx.navigation:navigation-fragment:2.5.2'
      implementation 'androidx.navigation:navigation-ui:2.5.2'

Jerarquía de Fragments:
<p align="center">
<img src="https://user-images.githubusercontent.com/113918779/198889508-7ebf9ba6-2912-45d9-9ec8-8442aff2b604.PNG" height="600" width="700" >
</p>
