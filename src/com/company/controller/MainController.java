package com.company.controller;

import com.company.model.authentication.AuthenticationService;
import com.company.model.dao.Book;
import com.company.model.service.BookService;

import java.io.IOException;
import java.util.List;

public class MainController {
    private final BookService bookService;
    private final AuthenticationService authenticationService;

    public MainController(BookService bookService, AuthenticationService authenticationService) {
        this.bookService = bookService;
        this.authenticationService = authenticationService;
    }

    public List<Book> getBooks() throws Exception {
        if(!authenticationService.isAuthorized()){
            throw new Exception("Forbidden");
        }

        return bookService.getAll();
    }

    public boolean authorizeUser(String email, String password) throws IOException {
        return authenticationService.authorizeUser(email, password);
    }
}
