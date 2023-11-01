package org.library.book_client.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.library.book_client.core.enums.BookGenre;

import java.util.UUID;

public class BookCreateDTO {
    @NotBlank(message = "Указание ISBN обязательно")
    @Size(max = 13, message = "Максимальный размер ISBN 13 символов")
    private String isbn;
    @NotBlank(message = "Указание названия обязательно")
    @Size(max = 60, message = "Максимальный размер названия 60 символов")
    private String title;
    @NotNull(message = "Указание жанра обязательно")
    @Size(max = 40, message = "Максимальный размер жанра 40 символов")
    private BookGenre genre;
    private String description;
    @NotNull(message = "Указание автора обязательно")
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
