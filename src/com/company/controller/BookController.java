package com.company.controller;

import com.company.model.authentication.AuthenticationService;
import com.company.model.dao.Book;
import com.company.model.service.BookService;
import com.company.model.service.MessageService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BookController {
    private final BookService bookService;
    private final MessageService messageService;
    private final AuthenticationService authenticationService;

    public BookController(BookService bookService, MessageService messageService,
                          AuthenticationService authenticationService) {
        this.bookService = bookService;
        this.messageService = messageService;
        this.authenticationService = authenticationService;
    }

    public void deleteBook(Map<String, String> arguments) throws IOException {
        bookService.deleteByIsbn(arguments.get("isbn"));
    }

    public void offerBook(Map<String, String> arguments){
        Book book = new Book(arguments.get("isbn"),
                arguments.get("name"),
                arguments.get("variant"));

        messageService.offerBook(book, authenticationService.getAuthenticationUser());
    }

    public void addBook(Map<String, String> arguments) throws IOException {
        Book book = new Book(arguments.get("isbn"),
                arguments.get("name"),
                arguments.get("variant"));

        bookService.create(book);
    }

    public List<Book> getBooks() throws IOException {
        return bookService.getAll();
    }
}
