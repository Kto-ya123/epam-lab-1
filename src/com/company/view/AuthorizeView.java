package com.company.view;

import com.company.command.CommandDefinition;
import com.company.command.CommandService;
import com.company.controller.MainController;
import com.company.model.authentication.AuthenticationUser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AuthorizeView implements View {
    private final MainController mainController;

    public AuthorizeView(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public String execute() throws IOException {
        for(int i = 0; i < 3; i++) {
            Map<String, String> arguments = new HashMap<>();
            Scanner in = new Scanner(System.in);
            System.out.print("Введите ваш email: ");
            arguments.put("username", in.nextLine());
            System.out.print("Введите ваш пароль: ");
            arguments.put("password", in.nextLine());

            if(mainController.authorizeUser(arguments)){
                return CommandService.MAIN_PAGE;
            }else {
                System.out.println("Неверное имя пользователя или пароль");
                System.out.println("\nОсталось " + (2 - i) +  " попыток");
            }
        }

        return null;

    }

    @Override
    public void setCommandDefinitions(List<CommandDefinition> commandDefinitions) {
    }

    @Override
    public void setAuthenticationUser(AuthenticationUser authenticationUser) {
    }
}
