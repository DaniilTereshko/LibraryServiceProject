package org.library.book_server.dao.repository;

import org.library.book_server.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IBookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findByIsbn(String isbn);
}
