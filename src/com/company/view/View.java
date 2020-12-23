package com.company.view;

import com.company.command.Command;
import com.company.command.CommandDefinition;
import com.company.model.authentication.AuthenticationUser;

import java.io.IOException;
import java.util.List;

/**
 * View.
 *
 * @author Artsiom Mazhylouski
 */
public interface View {
    String execute() throws IOException;

    void setCommandDefinitions(List<CommandDefinition> commandDefinitions);

    void setAuthenticationUser(AuthenticationUser authenticationUser);
}
