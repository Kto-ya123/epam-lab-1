package com.company.view;

import com.company.command.Command;
import com.company.command.CommandDefinition;
import com.company.command.CommandService;
import com.company.model.authentication.AuthenticationUser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * MainView.
 *
 * @author Artsiom Mazhylouski
 */
public class MainView extends BaseClassView {
    public MainView() {
    }

    @Override
    public String getPageName() {
        return CommandService.MAIN_PAGE;
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
