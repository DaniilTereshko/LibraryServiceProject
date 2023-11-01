package org.library.book_server.endpoints.web.controller;

import org.library.book_client.core.dto.BookCreateDTO;
import org.library.book_client.core.dto.BookDTO;
import org.library.book_client.core.dto.BookUpdateDTO;
import org.library.book_server.service.api.IBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.base_package.dto.PageDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class BookController{
    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookDTO> save(@RequestBody BookCreateDTO bookCreateDTO){
        return null;
    }
    @GetMapping
    public ResponseEntity<PageDTO<BookDTO>> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        return null;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BookDTO> get(@PathVariable UUID uuid){
        return null;
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<BookDTO> update(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") LocalDateTime updateDate,
            @RequestBody BookUpdateDTO bookUpdateDTO){
        return null;
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<BookDTO> delete(
            @PathVariable UUID uuid){
        return null;
    }
}
