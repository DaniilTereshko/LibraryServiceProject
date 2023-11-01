package org.library.book_server.config;

import jakarta.validation.Validator;
import org.library.book_server.core.converter.ConversionServiceFactory;
import org.library.book_server.dao.repository.IBookRepository;
import org.library.book_server.service.api.IBookService;
import org.library.book_server.service.impl.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookServiceConfig {
    @Bean
    public ConversionServiceFactory conversionService(){
        return new ConversionServiceFactory();
    }
    @Bean
    public IBookService bookService(IBookRepository bookRepository, Validator validator) {
        return new BookService(bookRepository, validator);
    }
}
