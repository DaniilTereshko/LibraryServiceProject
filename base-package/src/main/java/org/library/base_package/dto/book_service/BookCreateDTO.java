package org.library.base_package.dto.book_service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.library.base_package.enums.book_service.BookGenre;

public class BookCreateDTO {
    @NotBlank(message = "Указание ISBN обязательно")
    @Size(min = 13, max = 13, message = "Размер ISBN 13 символов")
    private String isbn;
    @NotBlank(message = "Указание названия обязательно")
    @Size(max = 60, message = "Максимальный размер названия 60 символов")
    private String title;
    @NotNull(message = "Указание жанра обязательно")
    private BookGenre genre;
    private String description;
    @NotBlank(message = "Указание автора обязательно")
    private String author;

    public BookCreateDTO() {
    }

    public BookCreateDTO(String isbn, String title, BookGenre genre, String description, String author) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
