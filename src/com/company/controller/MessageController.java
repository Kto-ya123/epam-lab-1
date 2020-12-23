package com.company.controller;

import com.company.model.authentication.AuthenticationService;
import com.company.model.dao.Message;
import com.company.model.service.MessageService;

import java.io.IOException;
import java.util.List;

public class MessageController {
    private final MessageService messageService;
    private final AuthenticationService authenticationService;

    public MessageController(MessageService messageService, AuthenticationService authenticationService) {
        this.messageService = messageService;
        this.authenticationService = authenticationService;
    }

    public List<Message> getMessages() throws IOException {
        return messageService.findByRole(authenticationService.getAuthenticationUser().getRole());
    }
}
