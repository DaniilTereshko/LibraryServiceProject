package org.library.book_server.endpoints.web.controller;

import org.library.book_server.service.api.IBookService;
import org.springframework.stereotype.Controller;

@Controller
public class BookController{
    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }
}
