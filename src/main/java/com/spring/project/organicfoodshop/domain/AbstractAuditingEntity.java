package com.spring.project.organicfoodshop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @CreatedDate
    @JsonProperty("created_at")
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @CreatedBy
    @JsonProperty("created_by")
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedDate
    @JsonProperty("updated_at")
    @Column(name = "updated_at", insertable = false)
    private Instant updatedAt;

    @LastModifiedBy
    @JsonProperty("update_by")
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;
}


