package com.ms.service;

import com.ms.entities.Book;
import com.ms.exceptions.BookNotFoundExpection;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book save(Book book);
    Book findOne(int id) throws BookNotFoundExpection;
    List<Book> findAll();
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    void remove(int id);
}
