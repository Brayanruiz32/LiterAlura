package com.literalura.literalura.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Book {

    @Id
    private Long id;

    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;

    private int totalDownloads;
    
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Author> authors = new ArrayList<Author>();;
    

    public Book(Long id, String title, List<String> language, int totalDownloads) {
        this.id = id;
        this.title = title;
        this.languages = language;
        this.totalDownloads = totalDownloads;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalDownloads() {
        return totalDownloads;
    }

    public void setTotalDownloads(int totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public List<String> getLanguage() {
        return languages;
    }

    public void setLanguage(List<String> language) {
        this.languages = language;
    }

    @Override
    public String toString() {
        return "----- LIBRO ---- \n Titulo: "+this.getTitle() 
        +" \n Autor(es): "+  String.join(" y ", this.getAuthors().stream().map(a -> a.getName()).collect(Collectors.toList())) 
        +" \n Idioma: "+ String.join(", ", this.getLanguage()) 
        +" \n Numero de descargas: "+this.getTotalDownloads()
        +" \n----------------";
    }

}
