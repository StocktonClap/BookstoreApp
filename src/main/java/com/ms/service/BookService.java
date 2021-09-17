package com.ms.service;

import com.ms.entities.Book;
import com.ms.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {

    Book save(Book book);
    Book getBookById(int id) throws BookNotFoundException;
    List<Book> getAll();
    List<Book> getBookByTitle(String title);
    List<Book> getBookByAuthor(String author);
    void updateBook(Book book);
    void remove(int id);
}
