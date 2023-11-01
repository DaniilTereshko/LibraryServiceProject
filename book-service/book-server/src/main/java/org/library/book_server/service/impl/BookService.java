package org.library.book_server.service.impl;

import org.library.book_client.core.dto.BookCreateDTO;
import org.library.book_client.core.dto.BookUpdateDTO;
import org.library.book_server.dao.entity.Book;
import org.library.book_server.dao.repository.IBookRepository;
import org.library.book_server.service.api.IBookService;
import org.springframework.data.domain.Page;

import java.util.UUID;

public class BookService implements IBookService {
    private final IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(BookCreateDTO item) {
        return null;
    }

    @Override
    public Page<Book> get(int page, int size) {
        return null;
    }

    @Override
    public Book get(UUID uuid) {
        return null;
    }

    @Override
    public Book update(BookUpdateDTO item) {
        return null;
    }

    @Override
    public Book delete(UUID uuid) {
        return null;
    }

    @Override
    public Book get(String isbn) {
        return null;
    }
}
