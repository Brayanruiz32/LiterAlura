package com.literalura.literalura.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
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
                } else {
                   // Optional<Author> autorActual = repoAuthor.findById(author.getId());
                    nuevoLibro.addAuthor(author);
                }
            }
            repoBook.save(nuevoLibro);// el libro se guarda
            System.out.println(nuevoLibro.toString());
        } else {
            System.out.println("No se puede registrar el mismo libro más de una vez");
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
        authors.forEach(a -> System.out.println(a.toString()));
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

    public void getStadisticData() {
        List<Book> books = repoBook.findAll();
        ArrayList<Book> libros = new ArrayList<Book>(books);
        DoubleSummaryStatistics data = libros.stream().collect(Collectors.summarizingDouble(Book::getTotalDownloads));
        System.out.println(
        "----- DATOS LIBROS -----"+
        "\nMedia de descargas: "+ String.format("%1.2f", data.getAverage())+
        "\nMayor descargada: "+data.getMax()+
        "\nMenor descargada "+data.getMin()+
        "\nCantidad de libros almacenados: "+data.getCount()+
        "\n------------------------------");

    }

    public void getTopBooks() {
        List<Book> libros = repoBook.encontrarTop10Libros();
        libros.forEach(System.out::println);
    }

    public void getAutor(String nombre) {
        Author author = repoBook.encontrarAutor(nombre); 
        System.out.println(author.toString());
    }

	public void getAuthorsAliveRange(int anioDesde, int anioHasta) {
		List<Author> autores = repoBook.encontrarAutoresVivosRango(anioDesde, anioHasta);
        autores.forEach(System.out::println);
	}




}
