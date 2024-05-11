package com.literalura.literalura;

import java.util.List;
import java.util.Scanner;

import com.literalura.literalura.dto.DataBook;
import com.literalura.literalura.dto.DataResults;
import com.literalura.literalura.service.BookService;
import com.literalura.literalura.service.ConexionAPI;
import com.literalura.literalura.service.ConvierteDatos;

public class Principal {

    private ConexionAPI connection = new ConexionAPI();
    private ConvierteDatos convertidor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private BookService servicio;

    public Principal(BookService servicio) {
        this.servicio = servicio;
    }

    public void logicaPrincipal() {

        String menu = """
                1 - Buscar libro por titulo
                2 - Listar libros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos en un determinado año
                5 - Listar libros por idioma
                6 - Salir de la aplicación
                """;
        System.out.println(menu);
        int opcion = Integer.parseInt(teclado.nextLine());
        while (opcion != 6) {
            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                // case 2:
                // listarLibros();
                // break;
                // case 3:
                // listarAutores();
                // break;
                // case 4:
                // listarAutoresVivos();
                // break;
                // case 5:
                // listarLibroXIdioma();
                // break;
                default:
                    break;
            }

            System.out.println(menu);
            opcion = Integer.parseInt(teclado.nextLine());

        }
        System.out.println("Saliendo...");
    }

    public void buscarLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        String titulo = teclado.nextLine();
        String json = connection.obtenerDatos(titulo);
        DataResults datos = convertidor.convertidora(json, DataResults.class);
        List<DataBook> resultados = datos.results();// tengo los resultados
        if (resultados.isEmpty()) {
            System.out.println("No se encontró el libro");

        } else {
            servicio.guardarLibro(resultados.get(0));
        }

    }

}
