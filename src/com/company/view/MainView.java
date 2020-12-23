package com.company.view;

import com.company.command.CommandDefinition;
import com.company.command.CommandService;

/**
 * MainView.
 *
 * @author Artsiom Mazhylouski
 */
public class MainView extends BaseClassView {
    public MainView() {
    }

    @Override
    public String execute(){
        clearScreen();
        printHeader();
        System.out.println("\n");
        printCommands();
        System.out.println("\n\n");

        CommandDefinition answer = getAnswer();
        switch (answer.getName()){
            case CommandService.OPEN_MAIL:{
                return CommandService.EMAIL_PAGE;
            }

            case CommandService.GET_BOOKS:{
                return CommandService.BOOK_PAGE;
            }

            case CommandService.QUIT:{
                return null;
            }

            default: return null;
        }
    }
}
