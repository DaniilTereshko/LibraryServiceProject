package org.library.book_server.service.impl;

import org.library.book_server.dao.repository.IBookRepository;
import org.library.book_server.service.api.IBookService;

public class BookService implements IBookService {
    private final IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
