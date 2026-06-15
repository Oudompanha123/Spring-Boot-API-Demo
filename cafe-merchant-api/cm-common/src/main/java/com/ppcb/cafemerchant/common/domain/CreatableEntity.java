package com.ppcb.cafemerchant.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class CreatableEntity {

    /**
     * The timestamp when the entity was created.
     * This field is automatically set when the entity is first persisted.
     */
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Instant createdAt;

    /**
     * The ID of the user who created the entity.
     * This field is automatically set based on the currently authenticated user.
     */
    @CreatedBy
    @Column(updatable = false, name = "created_by")
    private Integer createdBy;


}
