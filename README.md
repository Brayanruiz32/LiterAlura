<h1>LiterAlura</h1>
<img src="src/main/java/com/literalura/literalura/libroPortada.jpg" width="600">

## 📖 Descripción
El proyecto abarca una serie de funcionalidades implementadas y relacionadas a la persistencia de datos. Provee una interfaz intuitiva al usuario mediante consola para que pueda realizar las consultas acerca de libros y sus respectivos autores, mostrando datos del libro como de su autor ya guardadas previamente en una base de datos local. Este es un excelente proyecto en el que puedes comprobar la funcionalidad de persistencia de datos de forma local usando SpringBoot extrayendo datos de una API. 
## 🔨 Funcionalidades
- `Buscar libro por autor`: La aplicación realizará una petición mediante [HttpRequest](https://docs.oracle.com/en%2Fjava%2Fjavase%2F11%2Fdocs%2Fapi%2F%2F/java.net.http/java/net/http/HttpRequest.html) a la API de libros *(en el caso del proyecto es Gutendex)* y la recibirá mediante [HttpResponse](https://docs.oracle.com/en%2Fjava%2Fjavase%2F11%2Fdocs%2Fapi%2F%2F/java.net.http/java/net/http/HttpResponse.html), para luego utilizar una herramienta llamada [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson), el cual permitira deserializar la respuesta haciendo uso de un [DTO](https://www.arquitecturajava.com/data-transfer-object-dto-un-concepto-clave/)(Data Transfer Object) en forma de Record del libro y autor; y finalmente guardarla en la base de datos.
- `Listar libros registrados`: Se ejecutara un método de la aplicacion que hará una consulta a la base de datos(en nuestro caso mediante [Derived Query](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html)), se imprimira por consola todos los libros que se encuentran almacenados. 
- `Listar autores registrados`: Un metodo casi similar a la anterior con la diferencia que se realizará una consulta mediante `JOIN` para poder extraer todos los autores almacenados de la base de datos. 
- `Listar autores vivos en un determinado año`: Se le pedirá al usuario ingresar un determinado año, el cual servirá como parametro de busqueda en la consulta [JPQL](https://danielme.com/2023/03/09/curso-spring-data-jpa-consultas-jpql-con-query/), se imprimira por consola todos los autores vivos de ese año ingresado hacia adelante.
- `Listar libros por idioma`: El usuario ingresará una de las 4 opciones de los idiomas disponibles que se mostrarán por consola, y se hará consulta a la base de datos para extraer todos los libros que tenga esta caracteristica.  
- `Visualizar datos estadisticos`: Al escoger esta opción se podrá visualizar todos los datos estadisticos generales de los libros como el numero promedio de descargas, el numero maximo de descargas, el valor minimo de descargas, etc. 
- `Mostrar top 10 libros descargados`: Se visualizará por pantalla los 10 libros que tengan más descargas y que esten almacenadas en la base de datos.
- `Buscar autor por nombre`: Se le pedira al usuario que ingrese un nombre que servirá como parametro y la aplicacion ejecutará una consulta a la base de datos haciendo una relación `JOIN` y trayendo los datos del nombre del autor ingresado.
- `Listar autores que nacieron y murieron en un determinado rando de años`: Mediante consola el usuario ingresará un año que servirá como parametro de inicio y otro de fin para la consulta en la base de datos que devolverá una lista de autores que se encuentren tanto en nacimiento como en fallecimiento dentro de este rango.
## 🕹️ Uso
El uso de la aplicación es amigable para cualquier tipo de usuario que lo desee verificar y probar. Por lo siguiente, se hace una demostración del uso.
### Ejemplo de inserción a la base de datos (opción 1)
Esta será la salida por consola del menú con sus diferentes opciones:
```
--------------MENU--------------------
1 - Buscar libro por titulo
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma
6 - Visualizar datos estadisticos
7 - Mostrar top 10 libros descargados
8 - Buscar autor por nombre
9 - Listar autores que nacieron y murieron en un determinado rango de años
0 - Salir de la aplicación
```
1. Se selecciona la opción a ejecutar *(en nuestro caso la 1)* 
2. Se ingresará el titulo del libro *(en nuestro caso un fragmento del titulo de un libro llamado CRIME AND PUNI)*
```
Selecciona una de las opciones listadas

1
Ingrese el nombre del libro que desea buscar
CRIME AND PUNI
```
3. Para confirmar la inserción del nuevo libro se debe visualizar los datos del libro ingresado por consola
```
----- LIBRO ---- 
 Titulo: Crime and Punishment
 Autor(es): Dostoyevsky, Fyodor
 Idioma: en
 Numero de descargas: 23473
----------------
```
Te invito a probar las demás funcionalidades y a sumergirte en el tema por ti mismo 🤟
## ✔️ Tecnologias
Hice uso de las siguientes tecnologias para lograr la construccion de este proyecto.
- [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson)
- [Hibernate](https://hibernate.org/) 
- [Mysql](https://www.mysql.com/)
- [Postman](https://www.postman.com/)
- [SpringBoot](https://spring.io/projects/spring-boot)
- [Visual Studio Code](https://code.visualstudio.com/)
- [Spring Initializr](https://start.spring.io/)
- [Gutendex](https://gutendex.com/)

## 👨‍💻 Sobre el Autor
Soy Brayan Ruiz Marreros, al momento de subir este proyecto tengo 23 años, soy egresado de la carrera de Ingeniería de Sistemas e Informática, me motivan los desafios y me gusta aprender cosas nuevas en el rubro de la programación. En mis ratos libres veo alguna serie o pelicula y juego Age Of Empires🏰. 




