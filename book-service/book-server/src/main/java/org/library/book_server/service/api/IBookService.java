package org.library.book_server.service.api;

import org.library.book_client.core.dto.BookCreateDTO;
import org.library.book_client.core.dto.BookUpdateDTO;
import org.library.book_server.dao.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBookService {
    Book save(BookCreateDTO item);
    Page<Book> get(int page, int size);
    Book get(UUID uuid);
    Book update(BookUpdateDTO item);
    Book delete(UUID uuid);
    Book get(String isbn);
}
