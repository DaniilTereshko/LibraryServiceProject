package org.library.book_server.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.library.book_client.core.enums.BookGenre;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "books")
public class Book {
    @Id
    private UUID uuid;
    @Column(name = "isbn", nullable = false)
    private String isbn;
    @Column(name = "title", nullable = false)
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private BookGenre genre;
    @Column(name = "description")
    private String description;
    @Column(name = "author", nullable = false)
    private UUID author;
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "create_date", precision = 3)
    private LocalDateTime createDate;
    @UpdateTimestamp(source = SourceType.DB)
    @Version
    @Column(name = "update_date", precision = 3)
    private LocalDateTime updateDate;

    public Book() {
    }

    public Book(UUID uuid, String isbn, String title, BookGenre genre, String description, UUID author) {
        this.uuid = uuid;
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.author = author;
    }

    public Book(UUID uuid, String isbn, String title, BookGenre genre, String description, UUID author, LocalDateTime createDate, LocalDateTime updateDate) {
        this.uuid = uuid;
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.author = author;
        this.createDate = createDate;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (getUuid() != null ? !getUuid().equals(book.getUuid()) : book.getUuid() != null) return false;
        if (getIsbn() != null ? !getIsbn().equals(book.getIsbn()) : book.getIsbn() != null) return false;
        if (getTitle() != null ? !getTitle().equals(book.getTitle()) : book.getTitle() != null) return false;
        if (getGenre() != book.getGenre()) return false;
        if (getDescription() != null ? !getDescription().equals(book.getDescription()) : book.getDescription() != null)
            return false;
        if (getAuthor() != null ? !getAuthor().equals(book.getAuthor()) : book.getAuthor() != null) return false;
        if (getCreateDate() != null ? !getCreateDate().equals(book.getCreateDate()) : book.getCreateDate() != null)
            return false;
        return getUpdateDate() != null ? getUpdateDate().equals(book.getUpdateDate()) : book.getUpdateDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + (getIsbn() != null ? getIsbn().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getUpdateDate() != null ? getUpdateDate().hashCode() : 0);
        return result;
    }
}
