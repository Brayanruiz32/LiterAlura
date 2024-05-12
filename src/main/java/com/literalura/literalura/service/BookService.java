package com.literalura.literalura.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.literalura.literalura.dto.DataAuthor;
import com.literalura.literalura.dto.DataBook;
import com.literalura.literalura.model.Author;
import com.literalura.literalura.model.Book;
import com.literalura.literalura.repository.AuthorRepository;
import com.literalura.literalura.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private AuthorRepository repoAuthor;
    @Autowired
    private BookRepository repoBook;

    public void saveBook(DataBook d) {
        Optional<Book> libroBuscado = repoBook.findById(d.id());// busco si existe el libro
        if (!libroBuscado.isPresent()) {
            List<Author> autores = verifyAuthors(d.authors());// sustraigo y verifico si los autores ya estan almacenados       
            Book nuevoLibro = new Book(d.id(), d.title(), d.languages(), d.totalDownloads());// creo el nuevo libro
            for (Author author : autores) {
                if (author.getId() == null) {
                    nuevoLibro.addAuthor(author);
                    System.out.println("Se guardó el libro junto con un autor nuevo");
                } else {
                    Optional<Author> autorActual = repoAuthor.findById(author.getId());
                    nuevoLibro.addAuthor(autorActual.get());
                    System.out.println("Se guardó un nuevo libro con un autor ya existente");
                }
            }
            repoBook.save(nuevoLibro);// el libro se guarda
            System.out.println("Se ejecutó la carga del libro y autores a la bd");
        } else {
            System.out.println("Existe el libro en la BD por lo tanto no será guardado");
        }
    }

    public List<Author> verifyAuthors(List<DataAuthor> authors) {
        List<Author> autores = new ArrayList<>();
        for (DataAuthor a : authors) {
            Author autor = repoAuthor.buscarAutor(a.name(), a.birthYear(), a.deathYear());
            if (autor == null) {
                autores.add(new Author(a.name(), a.birthYear(), a.deathYear()));
            } else {
                autores.add(autor);
            }
        }
        return autores;
    }

    public void listBooks() {
        List<Book> books = repoBook.findAll();
        books.stream().forEach(System.out::println);
    }

    public void listAuthors() {
        List<Author> authors = repoBook.encontrarAutores();
        authors.stream().forEach(System.out::println);
    }

	public void listAuthorsAlive(int anio) {
        List<Author> authors = repoBook.encontrarAutoresVivos(anio);
        authors.stream().forEach(System.out::println);
	}

    public void listAvailableLanguages() {
         List<String> languages =  new ArrayList<>(repoBook.encontrarIdiomas());
         languages.stream().forEach(System.out::println);
    }

    public void listBooksByLanguage(String lenguaje) {
         List<Book> books = repoBook.encontrarLibroXIdioma(lenguaje); 
         books.stream().forEach(System.out::println);
    }




}
