package org.library.book_server.service.impl;

import org.library.book_client.core.dto.BookCreateDTO;
import org.library.book_client.core.dto.BookUpdateDTO;
import org.library.book_server.core.exception.NotFoundException;
import org.library.book_server.core.exception.VersionsMatchException;
import org.library.book_server.dao.entity.Book;
import org.library.book_server.dao.repository.IBookRepository;
import org.library.book_server.service.api.IBookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public class BookService implements IBookService {
    private static final String BOOK_NOT_FOUND = "Данная книга не найдена";
    private static final String VERSIONS_MATCH_ERROR = "Версии не совпадают";
    private final IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(BookCreateDTO item) {
        //валидация
        Book book = new Book(UUID.randomUUID(), item.getIsbn(), item.getTitle(),
                item.getGenre(), item.getDescription(), item.getAuthor());
        return this.bookRepository.save(book);
    }

    @Override
    public Page<Book> get(int page, int size) {
        //валидация
        PageRequest request = PageRequest.of(page, size);
        return this.bookRepository.findAll(request);
    }

    @Override
    public Book get(UUID uuid) {
        return this.bookRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND));
    }

    @Override
    public Book update(BookUpdateDTO item) {
        //валидация
        Book currentBook = this.get(item.getUuid());
        if (!item.getUpdateDate().isEqual(currentBook.getUpdateDate())) {
            throw new VersionsMatchException(VERSIONS_MATCH_ERROR);
        }
        currentBook.setIsbn(item.getIsbn());
        currentBook.setGenre(item.getGenre());
        currentBook.setDescription(item.getDescription());
        currentBook.setAuthor(currentBook.getAuthor());
        currentBook.setTitle(currentBook.getTitle());
        return this.bookRepository.save(currentBook);
    }

    @Override
    public Book delete(UUID uuid) {
        Book currentBook = this.get(uuid);
        this.bookRepository.delete(currentBook);
        return currentBook;
    }

    @Override
    public Book get(String isbn) {
        return this.bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND));
    }
}
