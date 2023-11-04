package org.library.library_service.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "records")
public class Record {
    @Id
    private UUID uuid;
    @Column(name = "\"from\"", precision = 3)
    private LocalDateTime from;
    @Column(name = "\"to\"", precision = 3)
    private LocalDateTime to;
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "dt_create", precision = 3)
    private LocalDateTime createDate;
    @UpdateTimestamp(source = SourceType.DB)
    @Version
    @Column(name = "dt_update", precision = 3)
    private LocalDateTime updateDate;

    public Record() {
    }

    public Record(UUID uuid, LocalDateTime from, LocalDateTime to) {
        this.uuid = uuid;
        this.from = from;
        this.to = to;
    }

    public Record(UUID uuid, LocalDateTime from, LocalDateTime to, LocalDateTime createDate, LocalDateTime updateDate) {
        this.uuid = uuid;
        this.from = from;
        this.to = to;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (getUuid() != null ? !getUuid().equals(record.getUuid()) : record.getUuid() != null) return false;
        if (getFrom() != null ? !getFrom().equals(record.getFrom()) : record.getFrom() != null) return false;
        if (getTo() != null ? !getTo().equals(record.getTo()) : record.getTo() != null) return false;
        if (getCreateDate() != null ? !getCreateDate().equals(record.getCreateDate()) : record.getCreateDate() != null)
            return false;
        return getUpdateDate() != null ? getUpdateDate().equals(record.getUpdateDate()) : record.getUpdateDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + (getFrom() != null ? getFrom().hashCode() : 0);
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getUpdateDate() != null ? getUpdateDate().hashCode() : 0);
        return result;
    }
}
