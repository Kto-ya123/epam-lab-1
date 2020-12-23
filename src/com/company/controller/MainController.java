package com.company.controller;

import com.company.model.authentication.AuthenticationService;

import java.io.IOException;
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
