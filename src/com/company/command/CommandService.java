package com.company.command;

import com.company.model.dao.Role;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CommandService.
 *
 * @author Artsiom Mazhylouski
 */
public class CommandService {
    public final static String GET_BOOKS = "all";
    public final static String GET_BOOKS_DESCRIPTION = "получить все книги";
    public final static String ADD_BOOK = "new";
    public final static String ADD_BOOK_DESCRIPTION = "создать книгу";
    public final static String DEL_BOOK = "rm";
    public final static String DEL_BOOK_DESCRIPTION = "удалить книгу";
    public final static String NEXT_PAGE = "n";
    public final static String NEXT_PAGE_DESCRIPTION = "следующая страница";
    public final static String PREV_PAGE = "p";
    public final static String PREV_PAGE_DESCRIPTION = "предыдущая страница";
    public final static String SEARCH_BOOKS = "filter";
    public final static String SEARCH_BOOKS_DESCRIPTION = "найти книгу";
    public final static String EXIT_BOOK_PAGE = "exit";
    public final static String EXIT_BOOK_PAGE_DESCRIPTION = "выйти с этой страницы";
    public final static String QUIT = "quit";
    public final static String QUIT_DESCRIPTION = "выйти из приложения";
    public final static String OPEN_MAIL = "mailbox";
    public final static String OPEN_MAIL_DESCRIPTION = "отрыть почту";
    public final static String OFFER_BOOK = "offer";
    public final static String OFFER_BOOK_DESCRIPTION = "предложить книгу";

    public final static String BOOK_PAGE = "books_page";
    public final static String MAIN_PAGE = "main_page";
    public final static String AUTHORIZE_PAGE = "authorize_page";
    public final static String EMAIL_PAGE = "email_page";


    private List<CommandDefinition> commandDefinitions;


    public CommandService() {
        initializeCommands();
    }

    private void initializeCommands() {
        commandDefinitions = Arrays.asList(
                new CommandDefinition(QUIT, QUIT_DESCRIPTION, Role.USER, MAIN_PAGE),
                new CommandDefinition(OFFER_BOOK, OFFER_BOOK_DESCRIPTION, Role.USER, MAIN_PAGE),
                new CommandDefinition(GET_BOOKS, GET_BOOKS_DESCRIPTION, Role.USER, MAIN_PAGE),
                new CommandDefinition(OPEN_MAIL, OPEN_MAIL_DESCRIPTION, Role.USER, MAIN_PAGE),
                new CommandDefinition(ADD_BOOK, ADD_BOOK_DESCRIPTION, Role.ADMIN, BOOK_PAGE),
                new CommandDefinition(DEL_BOOK, DEL_BOOK_DESCRIPTION, Role.ADMIN, BOOK_PAGE),
                new CommandDefinition(NEXT_PAGE, NEXT_PAGE_DESCRIPTION, Role.USER, BOOK_PAGE),
                new CommandDefinition(PREV_PAGE, PREV_PAGE_DESCRIPTION, Role.USER, BOOK_PAGE),
                new CommandDefinition(SEARCH_BOOKS, SEARCH_BOOKS_DESCRIPTION, Role.USER, BOOK_PAGE),
                new CommandDefinition(EXIT_BOOK_PAGE, EXIT_BOOK_PAGE_DESCRIPTION, Role.USER, BOOK_PAGE)

        );
    }

    public List<CommandDefinition> getCommandsByRoleAndPage(Role role, String page) {
        if (role.equals(Role.ADMIN)) {
            return commandDefinitions.stream()
                    .filter(command -> command.getPage().equals(page))
                    .collect(Collectors.toList());
        }

        return commandDefinitions.stream()
                .filter(command -> command.getRole().equals(role) && command.getPage().equals(page))
                .collect(Collectors.toList());
    }
}
