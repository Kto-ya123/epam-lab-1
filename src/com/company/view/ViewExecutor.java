package com.company.view;

import com.company.command.CommandDefinition;
import com.company.command.CommandService;
import com.company.controller.MainController;
import com.company.model.authentication.AuthenticationService;
import com.company.model.authentication.AuthenticationUser;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ViewExecutor.
 *
 * @author Artsiom Mazhylouski
 */
public class ViewExecutor {
    private Map<String, View> views;
    private String currentPage;
    private final AuthenticationService authService;
    private final CommandService commandService;

    public ViewExecutor(Map<String, View> views, AuthenticationService authService, CommandService commandService, MainController mainController) {
        this.views = views;
        this.authService = authService;
        this.commandService = commandService;
        this.currentPage = CommandService.MAIN_PAGE;
    }

    public void startApplication() throws IOException {
        if(!authUser()){
            return;
        }

        while (currentPage != null){
            View view = views.get(currentPage);
            this.currentPage = view.execute();
        }
    }

    public boolean authUser() throws IOException {
        String execute = views.get(CommandService.AUTHORIZE_PAGE).execute();
        if(Objects.nonNull(execute)) {
            updateViews();
            return true;
        }

        return false;
    }

    private void updateViews(){
        AuthenticationUser authUser = authService.getAuthenticationUser();

        views.forEach((pageName, view) -> {
            view.setAuthenticationUser(authUser);
            view.setCommandDefinitions(getCommands(authUser, pageName));
        });

    }

    private List<CommandDefinition> getCommands(AuthenticationUser authUser, String page){
        return commandService.getCommandsByRoleAndPage(authUser.getRole(), page);
    }
}
