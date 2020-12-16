package com.company.model.repository;

import com.company.model.dao.Book;
import com.company.model.dao.Role;
import com.company.model.dao.User;
import com.company.model.utils.StorageReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static final String fieldSeparator = "\r\n";
    private final String userStoragePath;

    public UserRepository(String userStoragePath) {
        this.userStoragePath = userStoragePath;
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) throws IOException {
        String[] storage = StorageReaderUtil.readFile(userStoragePath);
        List<User> users = new ArrayList<>();
        for (String record : storage) {
            String[] fields = record.split(fieldSeparator);
            users.add(new User(fields[0], fields[1], fields[2], Role.valueOf(fields[3])));
        }

        return users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst();
    }
}
