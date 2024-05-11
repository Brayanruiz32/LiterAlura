package com.literalura.literalura.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Book {

    @Id
    private Long id;

    private String title;

    private String language;

    private int totalDownloads;

    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> authors;
    

    public Book(Long id, String title, String language, int totalDownloads, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.totalDownloads = totalDownloads;
        this.authors = authors;
    }

    public Book(Long id, String title, String language, int totalDownloads) {
        this.id = id;
        this.title = title;
        this.language = language;
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

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", language=" + language + ", totalDownloads=" + totalDownloads
                + ", authors=" + authors + "]";
    }

}
