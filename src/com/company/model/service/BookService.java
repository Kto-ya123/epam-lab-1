package com.company.model.service;

import com.company.model.dao.Book;
import com.company.model.repository.BookRepository;

import java.io.IOException;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() throws IOException {
        return bookRepository.findAll();
    }
}
