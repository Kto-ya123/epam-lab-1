package com.company;

import com.company.controller.MainController;
import com.company.model.authentication.AuthenticationService;
import com.company.model.dao.Book;
import com.company.model.dao.User;
import com.company.model.repository.BookRepository;
import com.company.model.repository.UserRepository;
import com.company.model.service.BookService;
import com.company.view.MainView;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        if(args.length < 2){
            throw new Exception("args not valid");
        }

        MainView mainView = buildApplication(args[0], args[1]);
        mainView.start();
    }

    private static MainView buildApplication(String booksPath, String usersPath){
        BookRepository bookRepository = new BookRepository(booksPath);
        UserRepository userRepository = new UserRepository(usersPath);
        BookService bookService = new BookService(bookRepository);
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        MainController mainController = new MainController(bookService, authenticationService);
        MainView mainView = new MainView(mainController);

        return mainView;
    }
}
