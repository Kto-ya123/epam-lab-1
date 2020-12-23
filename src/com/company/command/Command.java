package com.company.command;

import java.util.Map;

/**
 * Command.
 *
 * @author Artsiom Mazhylouski
 */
public class Command {
    private CommandDefinition commandDefinition;
    private Map<String, String> arguments;

    public Command() {
    }

    public Command(CommandDefinition commandDefinition, Map<String, String> arguments) {
        this.commandDefinition = commandDefinition;
        this.arguments = arguments;
    }

    public CommandDefinition getCommandDefinition() {
        return commandDefinition;
    }

    public void setCommandDefinition(CommandDefinition commandDefinition) {
        this.commandDefinition = commandDefinition;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }
}
