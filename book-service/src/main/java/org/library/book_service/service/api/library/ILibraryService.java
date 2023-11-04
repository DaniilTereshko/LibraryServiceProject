package org.library.book_service.service.api.library;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ILibraryService {
    List<UUID> getFree();
    void save(UUID uuid, String from, String to);
    void delete(UUID uuid);
}
