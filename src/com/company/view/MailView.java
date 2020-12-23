package com.company.view;

import com.company.command.CommandService;
import com.company.controller.MessageController;
import com.company.model.dao.Message;

import java.io.IOException;

/**
 * MailView.
 *
 * @author Artsiom Mazhylouski
 */
public class MailView extends BaseClassView {
    private final MessageController messageController;

    public MailView(MessageController messageController) {
        this.messageController = messageController;
    }

    @Override
    public String execute() throws IOException {
        printHeader();
        System.out.println("\n\n");
        printMessages();
        System.out.println("Для выхода нажмите enter...");
        System.in.read();
        return CommandService.EMAIL_PAGE;
    }

    public void printMessages() throws IOException {
        for (Message message : messageController.getMessages()) {
            System.out.println("От кого: " + message.getFrom());
            System.out.println("Тип: " + message.getMessageType().toString());
            System.out.println("ISBN: " + message.getBook().getIsbn());
            System.out.println("Название: " + message.getBook().getName());
            System.out.println("Вариант: " + message.getBook().getVariant());
            System.out.println("\n\n");
        }
    }
}
