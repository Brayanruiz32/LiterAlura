package com.literalura.literalura.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // public List<Book> convertToBook(List<DataBook> datos) {

    //     List<Book> myBooks = datos.stream()
    //             .map(b -> new Book(b.id(), b.title(), b.language(), b.totalDownloads(),
    //                     b.authors().stream().map(a -> new Author(a.name(), a.birthYear(), a.deathYear()))
    //                             .collect(Collectors.toList())))
    //             .collect(Collectors.toList());
    //     return myBooks;

    // }

    public void guardarLibro(DataBook d) {
        Optional<Book> libroBuscado = repoBook.findById(d.id());
        if (!libroBuscado.isPresent()) {
            List<Author> autores = verifyAuthors(d.authors());
            //List<Book> libros = new ArrayList<>();
            for (Author author : autores) {
                if (author.getId() == null) {
                   Book libro =  new Book(d.id(), d.title(), d.language(), d.totalDownloads(), d.authors()
                   .stream().map(a -> new Author(a.name(), a.birthYear(), a.deathYear()))
                   .collect(Collectors.toList()));
                    repoBook.save(libro);
                    System.out.println("Se guardo el libro junto con sus autores");
                }else{
                    author.setBook(new Book(d.id(), d.title(), d.language(), d.totalDownloads()));
                }
            }
        } else {
            System.out.println("Existe el libro en la BD por lo tanto no será guardado");
        }
    }

    public List<Author> verifyAuthors(List<DataAuthor> authors){
        List<Author> autores = new ArrayList<>();
        for (DataAuthor a : authors) {  
            Author autor = repoAuthor.buscarAutor(a.name(), a.birthYear(), a.deathYear());
            if (autor == null) {
               autores.add(new Author(a.name(), a.birthYear(), a.deathYear()));
            }else{
                autores.add(autor);
            }
        }
        return autores;
    }


    // public void validarAutor(List<Author> autores) {

    //     for (Author author : autores) {
    //         String nombre = author.getName();
    //         int fechaNacimiento = author.getYearBirth();
    //         int fechaMuerte = author.getYearDeath();
    //         Author autor = null;
    //         autor = repoAuthor.buscarAutor(nombre, fechaNacimiento, fechaMuerte);
    //         if (autor == null) {// validamos que sea el unico autor
    //             repoAuthor.save(new Author(nombre, fechaNacimiento, fechaMuerte) );
    //             System.out.println("Guardado el autor exitosamente!!! :D");
    //         } else {
    //             System.out.println("El autor ya existe :(");
    //         }
    //     }
    // }

    // public List<Author> convertToAuthor(List<Book> books) {
    // List<Author> autores = books.stream().flatMap(b -> b.getAuthors().stream())
    // .collect(Collectors.toList());
    // List<Author> autoresFiltrados = new ArrayList<Author>();
    // for (Author author : autores) {
    // if (!autoresFiltrados.contains(author)) {
    // autoresFiltrados.add(author);
    // }
    // }
    // return autoresFiltrados;
    // }

}
