package org.library.base_package.dto.library_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecordDTO {
    private UUID uuid;
    @NotNull(message = "Указание даты начала обязательно")
    private String from;
    @NotNull(message = "Указание даты конца обязательно")
    private String to;
    @JsonProperty("dt_create")
    private Long createDate;
    @JsonProperty("dt_update")
    private Long updateDate;

    public RecordDTO() {
    }


    public RecordDTO(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public RecordDTO(UUID uuid, String from, String to, Long createDate, Long updateDate) {
        this.uuid = uuid;
        this.from = from;
        this.to = to;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
