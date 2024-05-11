package com.literalura.literalura.model;

import java.util.List;

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

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> book;

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
        return "Author [name=" + name + ", yearBirth=" + yearBirth + ", yearDeath=" + yearDeath + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book.add(book);
    }

    
    

}
