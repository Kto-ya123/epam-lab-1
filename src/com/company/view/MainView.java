package com.company.view;

import com.company.controller.MainController;
import com.company.model.dao.Book;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainView {
    private final MainController mainController;

    public MainView(MainController mainController) {
        this.mainController = mainController;
    }

    public void start() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите ваш email: ");
        String email = in.nextLine();
        System.out.print("Введите ваш password: ");
        String password = in.nextLine();

        if(!mainController.authorizeUser(email, password)){
            System.out.print("Вы не прошли авторизацию");
        }else {
            printBooks(mainController.getBooks());
        }

        System.in.read();

    }

    public void printBooks(List<Book> bookList){
        System.out.println("*********** All Books ************");
        bookList.forEach(book -> {
            System.out.println("ISBN: " + book.getIsbn() + "  Name: " + book.getName());
        });
    }

}
