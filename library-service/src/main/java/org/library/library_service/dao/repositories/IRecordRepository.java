package org.library.library_service.dao.repositories;

import org.library.library_service.dao.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public interface IRecordRepository extends JpaRepository<Record, UUID> {
    @Query("SELECT r FROM Record r WHERE :d NOT BETWEEN r.from AND r.to")
    List<Record> findFreeRecords(@Param("d") LocalDateTime d);
}
