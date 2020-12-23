package com.company.model.service;

import com.company.model.authentication.AuthenticationUser;
import com.company.model.dao.Book;
import com.company.model.dao.Message;
import com.company.model.dao.MessageType;
import com.company.model.dao.Role;
import com.company.model.repository.MessageRepository;

import java.io.IOException;
import java.util.List;

/**
 *  MessageService.
 *
 * @author Artsiom Mazhylouski
 */
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findByRole(Role role) throws IOException {
        if(role.equals(Role.USER)){
            return messageRepository.findByMessageType(MessageType.NOTIFICATION);
        }else {
            return messageRepository.findByMessageType(MessageType.OFFER);
        }
    }

    public void save(Message message){
        messageRepository.save(message);
    }

    public void offerBook(Book book, AuthenticationUser user) {
        Message message = new Message();
        message.setBook(book);
        message.setMessageType(MessageType.OFFER);
        message.setFrom(user.getUserName());
        messageRepository.save(message);
    }
}
