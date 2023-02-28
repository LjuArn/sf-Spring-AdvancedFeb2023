package com.example.demo.domain.dto;

import com.example.demo.domain.entity.AuthorEntity;
import jakarta.persistence.ManyToOne;

public class BookDto {

    private Long id;
    private String title;
    private String isbn;
    private AuthorEntity author;

    public BookDto() {
    }

    public Long getId() {
        return id;
    }

    public BookDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public BookDto setAuthor(AuthorEntity author) {
        this.author = author;
        return this;
    }
}
