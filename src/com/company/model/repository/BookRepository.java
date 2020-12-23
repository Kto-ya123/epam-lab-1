package com.company.model.repository;

import com.company.model.dao.Book;
import com.company.model.utils.StorageReaderUtil;
import com.company.model.utils.StorageWriterUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  BookRepository.
 *
 * @author Artsiom Mazhylouski
 */
public class BookRepository {
    private static final String fieldSeparator = "\r\n";
    private final String bookStoragePath;

    public BookRepository(String bookStoragePath) {
        this.bookStoragePath = bookStoragePath;
    }

    public Book save(Book book){
        StorageWriterUtil.writeRecord(bookStoragePath, bookToString(book));
        return book;
    }

    public List<Book> findAll() throws IOException {
        String[] storage = StorageReaderUtil.readFile(bookStoragePath);
        List<Book> books = new ArrayList<>();
        for (String record : storage) {
            String[] fields = record.split(fieldSeparator);
            books.add(new Book(fields[0], fields[1], fields[2]));
        }

        return books;
    }

    public Optional<Book> findByIsbn(String isbn) throws IOException {
        return findAll().stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
    }

    public void deleteByIsbn(String isbn) throws IOException {
        List<String> newStorage =
                findAll().stream().filter(book -> !book.getIsbn().equals(isbn))
                        .map(this::bookToString)
                        .collect(Collectors.toList());

        StorageWriterUtil.writeRecords(bookStoragePath, newStorage);
    }

    public String bookToString(Book book){
        StringBuilder bookRecord = new StringBuilder();
        bookRecord.append(book.getIsbn());
        bookRecord.append(fieldSeparator);
        bookRecord.append(book.getName());
        bookRecord.append(fieldSeparator);
        bookRecord.append(book.getVariant());
        return bookRecord.toString();
    }
}
