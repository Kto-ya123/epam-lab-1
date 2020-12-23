package com.company.model.repository;

import com.company.model.dao.*;
import com.company.model.utils.StorageReaderUtil;
import com.company.model.utils.StorageWriterUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  MessageRepository.
 *
 * @author Artsiom Mazhylouski
 */
public class MessageRepository {
    private static final String fieldSeparator = "\n";
    private final String mailStoragePath;

    public MessageRepository(String mailStoragePath) {
        this.mailStoragePath = mailStoragePath;
    }

    public List<Message> findAll() throws IOException {
        String[] storage = StorageReaderUtil.readFile(mailStoragePath);
        List<Message> messages = new ArrayList<>();
        for (String record : storage) {
            String[] fields = record.split(fieldSeparator);
            messages.add(new Message(fields[0], MessageType.valueOf(fields[1]),
                    new Book(fields[2], fields[3], fields[4])));
        }
        return messages;
    }

    public List<Message> findByMessageType(MessageType type) throws IOException {
        return findAll().stream()
                .filter(message -> message.getMessageType().equals(type))
                .collect(Collectors.toList());

    }

    public void save(Message message){

        StringBuilder messageRecord = new StringBuilder();
        messageRecord.append(message.getFrom());
        messageRecord.append(fieldSeparator);
        messageRecord.append(message.getMessageType().toString());
        messageRecord.append(fieldSeparator);
        messageRecord.append(message.getBook().getIsbn());
        messageRecord.append(fieldSeparator);
        messageRecord.append(message.getBook().getName());
        messageRecord.append(fieldSeparator);
        messageRecord.append(message.getBook().getVariant());

        StorageWriterUtil.writeRecord(mailStoragePath, messageRecord.toString());
    }
}
