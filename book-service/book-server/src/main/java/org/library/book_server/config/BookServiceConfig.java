package org.library.book_server.config;

import org.library.book_server.dao.repository.IBookRepository;
import org.library.book_server.service.api.IBookService;
import org.library.book_server.service.impl.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookServiceConfig {
    @Bean
    public IBookService bookService(IBookRepository bookRepository) {
        return new BookService(bookRepository);
    }
}
