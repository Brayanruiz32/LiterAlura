package com.literalura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.literalura.literalura.model.Author;
import com.literalura.literalura.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;



public interface BookRepository extends JpaRepository<Book, Long> {


    Optional<Book> findById(Long id);


    @Query("SELECT a FROM Book b JOIN b.authors a")
    List<Author> encontrarAutores();

    @Query("SELECT a FROM Book b JOIN b.authors a WHERE a.yearDeath >= :anio")
    List<Author> encontrarAutoresVivos(int anio);

    @Query("SELECT DISTINCT l FROM Book b JOIN b.languages l")
    Set<String> encontrarIdiomas();

    @Query("SELECT DISTINCT b FROM Book b JOIN b.languages l WHERE :lenguaje IN (l)")
    List<Book> encontrarLibroXIdioma(String lenguaje);

    @Query("SELECT b FROM Book b ORDER BY b.totalDownloads DESC LIMIT 10")
    List<Book> encontrarTop10Libros();

    @Query("SELECT a FROM Book b JOIN b.authors a WHERE a.name LIKE :nombre%")
    Author encontrarAutor(String nombre);

    @Query("SELECT a FROM Book b JOIN b.authors a WHERE a.yearBirth BETWEEN :anioDesde AND :anioHasta AND a.yearDeath BETWEEN :anioDesde AND :anioHasta")
    List<Author> encontrarAutoresVivosRango(int anioDesde, int anioHasta);


}
