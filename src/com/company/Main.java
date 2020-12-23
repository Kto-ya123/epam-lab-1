package com.company;

import com.company.command.CommandService;
import com.company.controller.BookController;
import com.company.controller.MainController;
import com.company.controller.MessageController;
import com.company.model.authentication.AuthenticationService;
import com.company.model.repository.BookRepository;
import com.company.model.repository.MessageRepository;
import com.company.model.repository.UserRepository;
import com.company.model.service.BookService;
import com.company.model.service.MessageService;
import com.company.view.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Main.
 *
 * @author Artsiom Mazhylouski
 */
public class Main {

    public static void main(String[] args) throws Exception {
        if(args.length < 1){
            throw new Exception("Неверные аргументы");
        }



        String bookPath = args[0] + "\\resources\\books.txt";
        String usersPath = args[0] + "\\resources\\users.txt";
        String emailPath = args[0] + "\\resources\\email.txt";

        buildApplication(bookPath, usersPath, emailPath).startApplication();
    }

    private static ViewExecutor buildApplication(String booksPath, String usersPath, String mailPath){
        BookRepository bookRepository = new BookRepository(booksPath);
        UserRepository userRepository = new UserRepository(usersPath);
        MessageRepository messageRepository = new MessageRepository(mailPath);
        MessageService messageService = new MessageService(messageRepository);
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        BookService bookService = new BookService(bookRepository, authenticationService, messageService);
        CommandService commandService = new CommandService();
        MainController mainController = new MainController(authenticationService);
        BookController bookController = new BookController(bookService, messageService, authenticationService);
        MessageController messageController = new MessageController(messageService, authenticationService);

        Map<String, View> views = new HashMap<>();
        views.put(CommandService.AUTHORIZE_PAGE, new AuthorizeView(mainController));
        views.put(CommandService.MAIN_PAGE, new MainView());
        views.put(CommandService.BOOK_PAGE, new BookView(bookController));
        views.put(CommandService.EMAIL_PAGE, new MailView(messageController));

        ViewExecutor viewExecutor = new ViewExecutor(views, authenticationService, commandService, mainController);

        return viewExecutor;
    }
}
