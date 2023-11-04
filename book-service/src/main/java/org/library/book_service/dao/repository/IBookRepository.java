package org.library.book_service.dao.repository;

import org.library.book_service.dao.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findFirstByIsbn(String isbn);
    Page<Book> findByUuidIn(List<UUID> uuid, Pageable pageable);
}
