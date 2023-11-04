package org.library.book_service.config;

import jakarta.validation.Validator;
import org.library.book_service.dao.repository.IBookRepository;
import org.library.book_service.endpoint.web.util.JwtHandler;
import org.library.book_service.service.api.book.IBookService;
import org.library.book_service.service.api.library.ILibraryService;
import org.library.book_service.service.api.library.ILibraryServiceClient;
import org.library.book_service.service.api.user.IUserService;
import org.library.book_service.service.api.user.IUserServiceClient;
import org.library.book_service.service.impl.book.BookService;
import org.library.book_service.service.impl.library.LibraryService;
import org.library.book_service.service.impl.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookServiceConfig {
    @Bean
    public IBookService bookService(IBookRepository bookRepository, ILibraryService libraryService, Validator validator) {
        return new BookService(bookRepository, libraryService, validator);
    }
    @Bean
    public ILibraryService libraryService(ILibraryServiceClient libraryServiceClient, JwtHandler jwtHandler) {
        return new LibraryService(libraryServiceClient, jwtHandler);
    }
    @Bean
    public IUserService userService(IUserServiceClient userServiceClient) {
        return new UserService(userServiceClient);
    }
}
