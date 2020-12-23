package com.company.view;

import com.company.command.Command;
import com.company.command.CommandDefinition;
import com.company.command.CommandService;
import com.company.controller.BookController;
import com.company.model.authentication.AuthenticationUser;
import com.company.model.dao.Book;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * BookView.
 *
 * @author Artsiom Mazhylouski
 */
public class BookView extends BaseClassView {
    private final static int PAGE_SIZE = 10;

    private final BookController bookController;

    private List<Book> books;
    private int numberPage;
    private int lastPage;

    public BookView(BookController bookController) {
        this.bookController = bookController;
    }

    @Override
    public String getPageName() {
        return CommandService.BOOK_PAGE;
    }

    @Override
    public String execute() throws IOException {
        while(true) {
            this.books = bookController.getBooks();
            numberPage = 1;
            lastPage = (this.books.size() / PAGE_SIZE) + 1;

            clearScreen();
            printHeader();
            System.out.println("\n");
            printAllBooks();
            System.out.println("\n");
            printCommands();
            System.out.println("\n\n");
            CommandDefinition answer = getAnswer();
            if(answer.getName().equals(CommandService.EXIT_BOOK_PAGE)){
                return CommandService.MAIN_PAGE;
            }else {
                doCommand(answer);
            }
        }

    }

    private void doCommand(CommandDefinition commandDefinition) throws IOException {
        switch (commandDefinition.getName()){
            case CommandService.ADD_BOOK:{
                bookController.addBook(buildAddBookArguments());
                break;
            }
            case CommandService.DEL_BOOK:{
                bookController.deleteBook(buildDelBookArguments());
                break;
            }
            case CommandService.OFFER_BOOK:{
                bookController.offerBook(buildAddBookArguments());
                break;
            }
            case CommandService.NEXT_PAGE:{
                if(numberPage != lastPage){
                    numberPage++;
                }
                break;
            }
            case CommandService.PREV_PAGE: {
                if(numberPage > 1){
                    numberPage--;
                }
                break;
            }
            case CommandService.SEARCH_BOOKS: {
                String searchArgument = buildSearchArgument();
                List<Book> searchBooks = this.books.stream()
                        .filter(book -> book.getName().contains(searchArgument)
                                || book.getIsbn().contains(searchArgument))
                        .collect(Collectors.toList());

                clearScreen();
                System.out.println("Ваш поиск по поисковой строке \"" + searchArgument + "\"\n");
                for (Book book : searchBooks) {
                    System.out.println("ISBN: " + book.getIsbn() + "\t Name: "
                            + book.getName() + "\t Variant: "
                            + book.getVariant());
                }
                System.out.println("\n\nНажмите enter ...");
                System.in.read();

            }
        }
    }

    public String buildSearchArgument() {
        Map<String, String> arguments = new HashMap<>();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку для поиска: ");
        return in.nextLine();
    }

    public Map<String, String> buildAddBookArguments(){
        Map<String, String> arguments = new HashMap<>();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите isbn: ");
        arguments.put("isbn", in.nextLine());
        System.out.print("Введите название: ");
        arguments.put("name", in.nextLine());
        System.out.print("Введите variant(p/e): ");
        arguments.put("variant", in.nextLine());

        return arguments;
    }

    public Map<String, String> buildDelBookArguments(){
        Map<String, String> arguments = new HashMap<>();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите isbn: ");
        arguments.put("isbn", in.nextLine());

        return arguments;
    }

    private void printAllBooks(){
        List<Book> bookList = this.books.stream()
                .skip((this.numberPage - 1) * PAGE_SIZE)
                .limit(PAGE_SIZE).collect(Collectors.toList());

        for (Book book : bookList) {
            System.out.println("ISBN: " + book.getIsbn() + "\t Название: " + book.getName()
                    + "\t Вариант: " + book.getVariant());
        }
        System.out.println("Страница " + numberPage +  " из " + lastPage + "\tВсего: " + this.books.size());
    }
}
