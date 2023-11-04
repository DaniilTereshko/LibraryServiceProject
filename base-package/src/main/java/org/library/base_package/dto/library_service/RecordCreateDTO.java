package org.library.base_package.dto.library_service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecordCreateDTO {
    private UUID book;
    @NotNull(message = "Указаните даты с обязательно")
    private LocalDateTime from;
    @NotNull(message = "Указаните даты по обязательно")
    private LocalDateTime to;

    public RecordCreateDTO() {
    }

    public RecordCreateDTO(UUID book, LocalDateTime from, LocalDateTime to) {
        this.book = book;
        this.from = from;
        this.to = to;
    }

    public UUID getBook() {
        return book;
    }

    public void setBook(UUID book) {
        this.book = book;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
