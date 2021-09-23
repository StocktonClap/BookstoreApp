package com.ms.controller;

import com.ms.entities.Book;
import com.ms.exceptions.BookNotFoundException;
import com.ms.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger logger = LogManager.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping(value = {"", "/"})
    public List<Book> getBooks() {
        logger.info("Retrieving all books.");
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") int id) throws BookNotFoundException {
        logger.info("Retrieving book by ID: " + id);
        return bookService.getBookById(id);
    }

    @GetMapping("/title")
    public List<Book> searchByTitle(@RequestBody String keyword) {
        return bookService.getBookByTitle(keyword);
    }

    @GetMapping("/author")
    public List<Book> searchByAuthor(@RequestBody String keyword) {
        return bookService.getBookByAuthor(keyword);
    }

    @PostMapping("")
    public Book addBook(@RequestBody Book book) {
        logger.info("Book has been added.");
        return bookService.save(book);
    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        bookService.save(book);
        return book;
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("id") int id,
                                         HttpServletResponse httpServletResponse,
                                         HttpServletRequest httpServletRequest) {
        try {
            Book book = bookService.getBookById(id);
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            String fileName = id + ".png";

            assert multipartFile != null;
            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book" + fileName)));
            stream.write(bytes);
            stream.close();
            return new ResponseEntity<>("Upload File - status: Success!", HttpStatus.OK);
        } catch (Exception | BookNotFoundException exception) {
            exception.printStackTrace();
            return new ResponseEntity<>("Upload File - status: Failed.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateImage/{id}")
    public ResponseEntity<?> updateImage(@RequestParam("id") int id,
                                         HttpServletResponse httpServletResponse,
                                         HttpServletRequest httpServletRequest) {
        try {
            Book book = bookService.getBookById(id);
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            String fileName = id + ".png";

            Files.delete(Paths.get("src/main/resources/static/image/book" + fileName));

            byte[] bytes = new byte[0];
            if (multipartFile != null) {
                bytes = multipartFile.getBytes();
            }
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book" + fileName)));
            stream.write(bytes);
            stream.close();
            return new ResponseEntity<>("Upload File - status: Success!", HttpStatus.OK);
        } catch (Exception | BookNotFoundException exception) {
            exception.printStackTrace();
            return new ResponseEntity<>("Upload File - status: Failed.", HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable String id) throws IOException {
        logger.info("Deleting book with ID: " + id);
        bookService.remove(Integer.parseInt(id));
        return new ResponseEntity<>("Remove Success", HttpStatus.OK);
    }
}