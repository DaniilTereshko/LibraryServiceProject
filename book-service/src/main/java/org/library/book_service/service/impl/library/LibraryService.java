package org.library.book_service.service.impl.library;

import org.library.book_service.endpoint.web.util.JwtHandler;
import org.library.book_service.service.api.library.ILibraryService;
import org.library.book_service.service.api.library.ILibraryServiceClient;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Component
public class LibraryService implements ILibraryService {
    private final ILibraryServiceClient libraryServiceClient;
    private final JwtHandler jwtHandler;

    public LibraryService(ILibraryServiceClient libraryServiceClient, JwtHandler jwtHandler) {
        this.libraryServiceClient = libraryServiceClient;
        this.jwtHandler = jwtHandler;
    }

    @Override
    public List<UUID> getFree() {
        String jwt = this.jwtHandler.generateSystemAccessToken("book-service");
        return this.libraryServiceClient.getFree("Bearer " + jwt).getBody();
    }

    @Override
    public void save(UUID uuid, String from, String to) {
        String jwt = this.jwtHandler.generateSystemAccessToken("book-service");
        this.libraryServiceClient.save("Bearer " + jwt, uuid, from, to);
    }

    @Override
    public void delete(UUID uuid) {
        String jwt = this.jwtHandler.generateSystemAccessToken("book-service");
        this.libraryServiceClient.delete("Bearer " + jwt, uuid);
    }
}
