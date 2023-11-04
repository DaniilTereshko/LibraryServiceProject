package org.library.base_package.dto.book_service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.library.base_package.enums.book_service.BookGenre;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookUpdateDTO {
    private UUID uuid;
    @NotBlank(message = "Указание ISBN обязательно")
    @Size(max = 13, message = "Максимальный размер ISBN 13 символов")
    private String isbn;
    @NotBlank(message = "Указание названия обязательно")
    @Size(max = 60, message = "Максимальный размер названия 60 символов")
    private String title;
    @NotNull(message = "Указание жанра обязательно")
    private BookGenre genre;
    private String description;
    @NotNull(message = "Указание автора обязательно")
    private String author;
    private LocalDateTime updateDate;

    public BookUpdateDTO() {
    }

    public BookUpdateDTO(UUID uuid, String isbn, String title, BookGenre genre, String description, String author, LocalDateTime updateDate) {
        this.uuid = uuid;
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.author = author;
        this.updateDate = updateDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
