package org.library.library_service.service.api.record;

import org.library.base_package.dto.library_service.RecordCreateDTO;
import org.library.library_service.dao.entity.Record;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface IRecordService {
    void save(RecordCreateDTO item);
    List<Record> getFree();
    Page<Record> get(int page, int size);
    void delete(UUID uuid);
}
