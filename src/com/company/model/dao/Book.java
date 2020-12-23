package com.company.model.dao;

/**
 * Book.
 *
 * @author Artsiom Mazhylouski
 */
public class Book {
    private String isbn;
    private String name;
    private String variant;

    public Book(String isbn, String name, String variant) {
        this.isbn = isbn;
        this.name = name;
        this.variant = variant;
    }

    public Book() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }
}
