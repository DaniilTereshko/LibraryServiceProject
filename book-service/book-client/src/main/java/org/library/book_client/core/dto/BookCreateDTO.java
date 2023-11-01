package org.library.book_client.core.dto;

import org.library.book_client.core.enums.BookGenre;

import java.util.UUID;

public class BookCreateDTO {
    private String isbn;
    private String title;
    private BookGenre genre;
    private String description;
    private UUID author;

    public BookCreateDTO() {
    }

    public BookCreateDTO(String isbn, String title, BookGenre genre, String description, UUID author) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getAuthor() {
        return author;
    }

    public void setAuthor(UUID author) {
        this.author = author;
    }
}
