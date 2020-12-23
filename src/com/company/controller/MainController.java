package com.company.controller;

import com.company.command.Command;
import com.company.command.CommandDefinition;
import com.company.command.CommandService;
import com.company.model.authentication.AuthenticationService;
import com.company.model.authentication.AuthenticationUser;
import com.company.model.service.BookService;
import com.company.model.service.MessageService;
import com.company.view.BookView;
import com.company.view.MailView;
import com.company.view.MainView;
import com.company.view.View;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * MainController.
 *
 * @author Artsiom Mazhylouski
 */
public class MainController {
    private final AuthenticationService authenticationService;

    public MainController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public boolean authorizeUser(Map<String, String> arguments) throws IOException {
        return authenticationService
                .authorizeUser(arguments.get("username"), arguments.get("password"));
    }
}
