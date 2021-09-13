package com.ms.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Book implements Serializable {

    public static final long serialVersionUID = 245489L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Date publicationDate;
    private String language;
    private String category;
    @Column(columnDefinition = "text")
    private String description;
    private int numberOfPages;
    private double price;
    private String bookSize;
    private int stockNumber;
    private boolean active=true;
    @Transient
    private MultipartFile bookImage;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public Book setId(int id) {
        this.id = id;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Book setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Book setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Book setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public Book setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Book setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getBookSize() {
        return bookSize;
    }

    public Book setBookSize(String bookSize) {
        this.bookSize = bookSize;
        return this;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public Book setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Book setActive(boolean active) {
        this.active = active;
        return this;
    }

    public MultipartFile getBookImage() {
        return bookImage;
    }

    public Book setBookImage(MultipartFile bookImage) {
        this.bookImage = bookImage;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publicationDate=" + publicationDate +
                ", language='" + language + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", price=" + price +
                ", bookSize=" + bookSize +
                ", stockNumber=" + stockNumber +
                ", active=" + active +
                ", bookImage=" + bookImage +
                '}';
    }
}
