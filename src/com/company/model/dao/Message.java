package com.company.model.dao;

/**
 *  Message.
 *
 * @author Artsiom Mazhylouski
 */
public class Message {
    String from;
    MessageType messageType;
    Book book;

    public Message(String from, MessageType messageType, Book book) {
        this.from = from;
        this.messageType = messageType;
        this.book = book;
    }

    public Message() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
