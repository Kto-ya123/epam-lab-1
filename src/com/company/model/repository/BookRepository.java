package com.company.model.repository;

import com.company.model.dao.Book;
import com.company.model.utils.StorageReaderUtil;
import com.company.model.utils.StorageWriterUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static final String fieldSeparator = "\r\n";
    private final String bookStoragePath;

    public BookRepository(String bookStoragePath) {
        this.bookStoragePath = bookStoragePath;
    }

    public Book save(Book book){
        StringBuilder bookRecord = new StringBuilder();
        bookRecord.append(book.getIsbn());
        bookRecord.append(fieldSeparator);
        bookRecord.append(book.getName());
        StorageWriterUtil.writeRecord(bookStoragePath, bookRecord.toString());
        return book;
    }

    public List<Book> findAll() throws IOException {
        String[] storage = StorageReaderUtil.readFile(bookStoragePath);
        List<Book> books = new ArrayList<>();
        for (String record : storage) {
            String[] fields = record.split(fieldSeparator);
            books.add(new Book(fields[0], fields[1]));
        }

        return books;
    }
}
