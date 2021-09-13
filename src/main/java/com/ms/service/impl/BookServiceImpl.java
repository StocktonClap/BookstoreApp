package com.ms.service.impl;

import com.ms.entities.Book;
import com.ms.exceptions.BookNotFoundExpection;
import com.ms.repository.BookRepository;
import com.ms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findOne(int id) throws BookNotFoundExpection {
        Optional<Book> result = bookRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new BookNotFoundExpection("Could not find any book by ID " + id);
    }

    @Override
    public List<Book> findAll() {

        List<Book> bookList = (List<Book>) bookRepository.findAll();

        List<Book> availableBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                availableBookList.add(book);
            }
        }
        return availableBookList;
    }

    @Override
    public List<Book> findByTitle(String title) {

        List<Book> bookList = bookRepository.findByTitleContaining(title);

        List<Book> availableBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                availableBookList.add(book);
            }
        }
        return availableBookList;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> bookList = bookRepository.findByAuthor(author);

        List<Book> availableBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                availableBookList.add(book);
            }
        }
        return availableBookList;
    }

    @Override
    public void remove(int id) {
        bookRepository.deleteById(id);
    }
}
