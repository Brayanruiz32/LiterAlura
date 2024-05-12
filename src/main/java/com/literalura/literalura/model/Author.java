package com.literalura.literalura.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 

    private int yearBirth;

    private int yearDeath;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author(String name, int yearBirth, int yearDeath) {
        this.name = name;
        this.yearBirth = yearBirth;
        this.yearDeath = yearDeath;  
    } 

    public Author() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public int getYearDeath() {
        return yearDeath;
    }

    public void setYearDeath(int yearDeath) {
        this.yearDeath = yearDeath;
    }

    @Override
    public String toString() {
        return " Autor: "+this.name+
        "\n Fecha de Nacimiento: "+this.yearBirth+
        "\n Fecha de Fallecimiento: "+this.yearDeath+
        "\n Libros: "+ this.getBook().stream().map(b -> b.getTitle()).collect(Collectors.toList())+
        "\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBook() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

}
