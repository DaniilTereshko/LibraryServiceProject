package org.library.book_client.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.library.book_client.core.enums.BookGenre;

import java.util.UUID;

public class BookUpdateDTO {
    private UUID uuid;
    private String isbn;
    private String title;
    private BookGenre genre;
    private String description;
    private UUID author;
    @JsonProperty("dt_update")
    private Long updateDate;

    public BookUpdateDTO() {
    }

    public BookUpdateDTO(UUID uuid, String isbn, String title, BookGenre genre, String description, UUID author, Long updateDate) {
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

    public UUID getAuthor() {
        return author;
    }

    public void setAuthor(UUID author) {
        this.author = author;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
