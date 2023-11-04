package org.library.book_service.service.api.book;

import org.library.base_package.dto.book_service.BookCreateDTO;
import org.library.base_package.dto.book_service.BookUpdateDTO;
import org.library.book_service.dao.entity.Book;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IBookService {
    Book save(BookCreateDTO item, String from, String to);
    Page<Book> get(int page, int size);
    Book get(UUID uuid);
    Book update(BookUpdateDTO item);
    Book delete(UUID uuid);
    Book get(String isbn);
    Page<Book> getFree(int page, int size);
}
