package com.ms.repository;

import com.ms.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByAuthor(String author);
}
