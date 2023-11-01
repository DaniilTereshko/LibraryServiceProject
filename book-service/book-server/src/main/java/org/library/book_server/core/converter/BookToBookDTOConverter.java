package org.library.book_server.core.converter;

import org.library.book_client.core.dto.BookDTO;
import org.library.book_server.dao.entity.Book;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneOffset;

public class BookToBookDTOConverter implements Converter<Book, BookDTO> {
    @Override
    public BookDTO convert(Book book) {
        return new BookDTO(book.getUuid(), book.getIsbn(), book.getTitle(), book.getGenre(),
                book.getDescription(), book.getAuthor(),
                book.getCreateDate().toInstant(ZoneOffset.UTC).toEpochMilli(), book.getUpdateDate().toInstant(ZoneOffset.UTC).toEpochMilli());
    }
}
