package com.ms.repository;

import com.ms.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> getBookByTitleContaining(String title);
    List<Book> getBookByAuthor(String author);
}
