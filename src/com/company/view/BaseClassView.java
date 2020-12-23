package com.company.view;

import com.company.command.CommandDefinition;
import com.company.model.authentication.AuthenticationUser;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * BaseClassView.
 *
 * @author Artsiom Mazhylouski
 */
public abstract class BaseClassView implements View {
    protected List<CommandDefinition> commandDefinitions;
    protected AuthenticationUser authenticationUser;

    protected BaseClassView() {
    }

    public void setCommandDefinitions(List<CommandDefinition> commandDefinitions) {
        this.commandDefinitions = commandDefinitions;
    }

    public void setAuthenticationUser(AuthenticationUser authenticationUser) {
        this.authenticationUser = authenticationUser;
    }

    protected void printCommands(){
        for (CommandDefinition commandDefinition : this.commandDefinitions) {
            System.out.println(commandDefinition.getName() + " - " + commandDefinition.getDescription());
        }
    }

    protected Optional<CommandDefinition> defineCommand(String commandName){
        return this.commandDefinitions.stream().filter(command -> command.getName().equals(commandName)).findFirst();
    }

    protected void printHeader(){
        System.out.println("Пользователь: " + authenticationUser.getUserName()
                + "\tРоль: " + authenticationUser.getRole().toString());
    }

    protected void clearScreen(){
        for(int i = 0; i < 80; i++){
            System.out.println("\n");
        }
    }

    protected CommandDefinition getAnswer(){
        while(true) {
            System.out.println("Введите комманду");
            Scanner in = new Scanner(System.in);
            String commandName = in.nextLine();
            Optional<CommandDefinition> commandOptional = defineCommand(commandName);
            if (commandOptional.isPresent()) {
                return commandOptional.get();
            } else {
                printCommands();
                clearScreen();
                System.out.println("Неверная комманда!!!\n");
            }
        }
    }
}
