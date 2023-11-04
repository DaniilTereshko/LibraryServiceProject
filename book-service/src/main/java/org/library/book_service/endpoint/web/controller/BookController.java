package org.library.book_service.endpoint.web.controller;

import org.library.base_package.dto.PageDTO;
import org.library.base_package.dto.book_service.BookCreateDTO;
import org.library.base_package.dto.book_service.BookDTO;
import org.library.base_package.dto.book_service.BookUpdateDTO;
import org.library.book_service.dao.entity.Book;
import org.library.book_service.endpoint.web.util.PageConverter;
import org.library.book_service.service.api.book.IBookService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController{
    private final IBookService bookService;
    private final PageConverter pageConverter;
    private final ConversionService conversionService;

    public BookController(IBookService bookService, PageConverter pageConverter, ConversionService conversionService) {
        this.bookService = bookService;
        this.pageConverter = pageConverter;
        this.conversionService = conversionService;
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookDTO> save(
            @RequestBody BookCreateDTO bookCreateDTO,
            @RequestParam("from") String from,
            @RequestParam("to") String to){
        Book savedBook = this.bookService.save(bookCreateDTO, from, to);
        BookDTO bookDTO = this.conversionService.convert(savedBook, BookDTO.class);
        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageDTO<BookDTO>> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        Page<Book> books = this.bookService.get(page, size);
        PageDTO<BookDTO> bookPageDTO = this.pageConverter.convertPageToDTO(books, BookDTO.class);
        return new ResponseEntity<>(bookPageDTO, HttpStatus.OK);
    }
    @GetMapping("/free")
    public ResponseEntity<PageDTO<BookDTO>> getFree(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Page<Book> books = this.bookService.getFree(page, size);
        PageDTO<BookDTO> bookPageDTO = this.pageConverter.convertPageToDTO(books, BookDTO.class);
        return new ResponseEntity<>(bookPageDTO, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BookDTO> get(@PathVariable UUID uuid){
        Book book = this.bookService.get(uuid);
        BookDTO bookDTO = this.conversionService.convert(book, BookDTO.class);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> get(@PathVariable String isbn){
        Book book = this.bookService.get(isbn);
        BookDTO bookDTO = this.conversionService.convert(book, BookDTO.class);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<BookDTO> update(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") LocalDateTime updateDate,
            @RequestBody BookUpdateDTO bookUpdateDTO){
        bookUpdateDTO.setUuid(uuid);
        bookUpdateDTO.setUpdateDate(updateDate);
        Book updatedBook = this.bookService.update(bookUpdateDTO);
        BookDTO bookDTO = this.conversionService.convert(updatedBook, BookDTO.class);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<BookDTO> delete(
            @PathVariable UUID uuid){
        Book deletedBook = this.bookService.delete(uuid);
        BookDTO bookDTO = this.conversionService.convert(deletedBook, BookDTO.class);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
}
