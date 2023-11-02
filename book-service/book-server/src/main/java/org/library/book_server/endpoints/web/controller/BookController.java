package org.library.book_server.endpoints.web.controller;

import org.library.book_client.core.dto.BookCreateDTO;
import org.library.book_client.core.dto.BookDTO;
import org.library.book_client.core.dto.BookUpdateDTO;
import org.library.book_server.dao.entity.Book;
import org.library.book_server.endpoints.web.util.PageConverter;
import org.library.book_server.service.api.IBookService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.library.base_package.dto.PageDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
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
    public ResponseEntity<BookDTO> save(@RequestBody BookCreateDTO bookCreateDTO){
        Book savedBook = this.bookService.save(bookCreateDTO);
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

    @GetMapping("/{uuid}")
    public ResponseEntity<BookDTO> get(@PathVariable UUID uuid){
        Book book = this.bookService.get(uuid);
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
