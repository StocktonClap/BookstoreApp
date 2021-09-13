package com.ms.controller;

import com.ms.entities.Book;
import com.ms.exceptions.BookNotFoundExpection;
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
@RequestMapping("/book")
public class BookController {

    private static final Logger logger = LogManager.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/bookList")
    public List<Book> getBookList() {
        logger.info("Retrieving all books.");
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") int id) throws BookNotFoundExpection {
        logger.info("Retrieving book by ID: " + id);
        return bookService.findOne(id);
    }

    @GetMapping("/search/title")
    public List<Book> searchByTitle(@RequestBody String keyword) {
        return bookService.findByTitle(keyword);
    }

    @GetMapping("/search/author")
    public List<Book> searchByAuthor(@RequestBody String keyword) {
        return bookService.findByAuthor(keyword);
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        logger.info("Book has been added.");
        return bookService.save(book);
    }

    @PostMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        logger.info("Updating book.");
        return bookService.save(book);
    }

    @PostMapping("/add/image")
    public ResponseEntity uploadImage(@RequestParam("id") int id,
                                      HttpServletResponse httpServletResponse,
                                      HttpServletRequest httpServletRequest) {
        try {
            Book book = bookService.findOne(id);
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            String fileName = id + ".png";

            assert multipartFile != null;
            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book" + fileName)));
            stream.write(bytes);
            stream.close();
            return new ResponseEntity("Upload File - status: Success!", HttpStatus.OK);
        } catch (Exception | BookNotFoundExpection exception) {
            exception.printStackTrace();
            return new ResponseEntity("Upload File - status: Failed.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update/image")
    public ResponseEntity updateImage(@RequestParam("id") int id,
                                      HttpServletResponse httpServletResponse,
                                      HttpServletRequest httpServletRequest) {
        try {
            Book book = bookService.findOne(id);
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            String fileName = id + ".png";

            Files.delete(Paths.get("src/main/resources/static/image/book" + fileName));

            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book" + fileName)));
            stream.write(bytes);
            stream.close();
            return new ResponseEntity("Upload File - status: Success!", HttpStatus.OK);
        } catch (Exception | BookNotFoundExpection exception) {
            exception.printStackTrace();
            return new ResponseEntity("Upload File - status: Failed.", HttpStatus.BAD_REQUEST);
        }
    }


            @DeleteMapping("/remove")
            public ResponseEntity removeBook ( @RequestBody int id) throws IOException {
                logger.info("Deleting book with ID: " + id);
                bookService.remove(id);
                String fileName = id + ".png";
                Files.delete(Paths.get("src/main/resources/static/image/book" + fileName));
                return new ResponseEntity("Remove Success", HttpStatus.OK);
            }
        }