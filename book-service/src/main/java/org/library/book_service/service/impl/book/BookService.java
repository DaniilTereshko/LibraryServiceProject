package org.library.book_service.service.impl.book;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.library.base_package.dto.book_service.BookCreateDTO;
import org.library.base_package.dto.book_service.BookUpdateDTO;
import org.library.book_service.core.exception.IncorrectDataException;
import org.library.book_service.core.exception.NotFoundException;
import org.library.book_service.core.exception.VersionsMatchException;
import org.library.book_service.dao.entity.Book;
import org.library.book_service.dao.repository.IBookRepository;
import org.library.book_service.service.api.book.IBookService;
import org.library.book_service.service.api.library.ILibraryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BookService implements IBookService {
    private static final String BOOK_NOT_FOUND = "Данная книга не найдена";
    private static final String VERSIONS_MATCH_ERROR = "Версии не совпадают";
    private static final String INCORRECT_DATA = "Неверные данные. Попробуйте еще раз";
    private final IBookRepository bookRepository;
    private final ILibraryService libraryService;
    private final Validator validator;

    public BookService(IBookRepository bookRepository, ILibraryService libraryService, Validator validator) {
        this.bookRepository = bookRepository;
        this.libraryService = libraryService;
        this.validator = validator;
    }
    @Transactional
    @Override
    public Book save(BookCreateDTO item, String from, String to) {
        this.validate(item);
        UUID uuid = UUID.randomUUID();
        Book book = new Book(uuid, item.getIsbn(), item.getTitle(),
                item.getGenre(), item.getDescription(), item.getAuthor());
        this.libraryService.save(uuid, from, to);
        return this.bookRepository.save(book);
    }
    @Transactional(readOnly = true)
    @Override
    public Page<Book> get(int page, int size) {
        if(page < 0 || size < 0){
            throw new IncorrectDataException(INCORRECT_DATA);
        }
        PageRequest request = PageRequest.of(page, size);
        return this.bookRepository.findAll(request);
    }
    @Transactional(readOnly = true)
    @Override
    public Book get(UUID uuid) {
        return this.bookRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND));
    }
    @Transactional
    @Override
    public Book update(BookUpdateDTO item) {
        this.validate(item);
        Book currentBook = this.get(item.getUuid());
        if (!item.getUpdateDate().isEqual(currentBook.getUpdateDate())) {
            throw new VersionsMatchException(VERSIONS_MATCH_ERROR);
        }
        currentBook.setIsbn(item.getIsbn());
        currentBook.setGenre(item.getGenre());
        currentBook.setDescription(item.getDescription());
        currentBook.setAuthor(item.getAuthor());
        currentBook.setTitle(item.getTitle());
        return this.bookRepository.save(currentBook);
    }
    @Transactional
    @Override
    public Book delete(UUID uuid) {
        Book currentBook = this.get(uuid);
        this.libraryService.delete(uuid);
        this.bookRepository.delete(currentBook);
        return currentBook;
    }
    @Transactional(readOnly = true)
    @Override
    public Book get(String isbn) {
        return this.bookRepository.findFirstByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND));
    }
    @Transactional(readOnly = true)
    @Override
    public Page<Book> getFree(int page, int size) {
        List<UUID> uuids = this.libraryService.getFree();
        if(page < 0 || size < 0){
            throw new IncorrectDataException(INCORRECT_DATA);
        }
        PageRequest request = PageRequest.of(page, size);
        return this.bookRepository.findByUuidIn(uuids, request);
    }

    private <T> void validate(T item) {
        Set<ConstraintViolation<T>> constraintViolations = this.validator.validate(item);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
