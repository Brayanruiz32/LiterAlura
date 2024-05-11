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

    public void guardarLibro(DataBook d) {
        Optional<Book> libroBuscado = repoBook.findById(d.id());//busco si existe el libro
        if (!libroBuscado.isPresent()) {
            List<Author> autores = verifyAuthors(d.authors());//sustraigo y verifico si los autores ya estan almacenados
            //List<Book> libros = new ArrayList<>();
            for (Author author : autores) {
                if (author.getId() == null) {
                   Book libro =  new Book(d.id(), d.title(), d.languages(), d.totalDownloads(), d.authors()
                   .stream().map(a -> new Author(a.name(), a.birthYear(), a.deathYear()))
                   .collect(Collectors.toList()));
                    //primero crea el autor, luego lo setea al libro el autor



                    repoBook.save(libro);//solamente estoy guardando el libro
                    System.out.println("Se guardo el libro junto con un autor nuevo");
                }else{
                    Book nuevoLibro = new Book(d.id(), d.title(), d.languages(), d.totalDownloads());//creo el nuevo libro
                    nuevoLibro.setAuthors(author);//agrego el autor al nuevo libro 
                    author.setBook(nuevoLibro);//el autor agrega el nuevo libro 
                    repoAuthor.save(author);//el autor se actualiza(Solo estoy actualizando el autor)
                    System.out.println("Se guardo un nuevo libro con un autor ya existente");
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

}
