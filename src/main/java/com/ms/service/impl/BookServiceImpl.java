package com.ms.service.impl;

import com.ms.entities.Book;
import com.ms.exceptions.BookNotFoundException;
import com.ms.repository.BookRepository;
import com.ms.service.BookService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(int id) throws BookNotFoundException {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found."));
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBookByTitle(String title) {

        List<Book> bookList = bookRepository.getBookByTitleContaining(title);

        List<Book> availableBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                availableBookList.add(book);
            }
        }
        return availableBookList;
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        List<Book> bookList = bookRepository.getBookByAuthor(author);

        List<Book> availableBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                availableBookList.add(book);
            }
        }
        return availableBookList;
    }

    @Override
    public void updateBook(Book book) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(book);
        }

    @Override
    public void remove(int id) {
        bookRepository.deleteById(id);
    }
}
