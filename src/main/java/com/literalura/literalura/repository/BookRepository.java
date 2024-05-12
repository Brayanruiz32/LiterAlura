package com.literalura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.literalura.literalura.model.Author;
import com.literalura.literalura.model.Book;

import java.util.List;
import java.util.Optional;



public interface BookRepository extends JpaRepository<Book, Long> {


    Optional<Book> findById(Long id);


    @Query("SELECT a FROM Book b JOIN b.authors a")
    List<Author> encontrarAutores();

    @Query("SELECT a FROM Book b JOIN b.authors a WHERE a.yearDeath >= :anio")
    List<Author> encontrarAutoresVivos(int anio);

    @Query("SELECT DISTINCT(b.languages) FROM Book b")
    List<String> encontrarIdiomas();





}
