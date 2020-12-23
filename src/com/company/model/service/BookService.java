package com.company.model.service;

import com.company.model.authentication.AuthenticationService;
import com.company.model.dao.Book;
import com.company.model.dao.Message;
import com.company.model.dao.MessageType;
import com.company.model.repository.BookRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 *  BookService.
 *
 * @author Artsiom Mazhylouski
 */
public class BookService {
    private final BookRepository bookRepository;
    private final AuthenticationService authenticationService;
    private final MessageService messageService;


    public BookService(BookRepository bookRepository, AuthenticationService authenticationService,
                       MessageService messageService) {
        this.bookRepository = bookRepository;
        this.authenticationService = authenticationService;
        this.messageService = messageService;
    }

    public List<Book> getAll() throws IOException {
        return bookRepository.findAll();
    }

    public void create(Book book) throws IOException {
        Optional<Book> bookOptional = bookRepository.findByIsbn(book.getIsbn());
        if(!bookOptional.isPresent()){
            bookRepository.save(book);
        }

        Message message = new Message();
        message.setBook(book);
        message.setMessageType(MessageType.NOTIFICATION);
        message.setFrom(authenticationService.getAuthenticationUser().getUserName());
        messageService.save(message);
    }

    public void deleteByIsbn(String isbn) throws IOException {
        bookRepository.deleteByIsbn(isbn);

    }
}
