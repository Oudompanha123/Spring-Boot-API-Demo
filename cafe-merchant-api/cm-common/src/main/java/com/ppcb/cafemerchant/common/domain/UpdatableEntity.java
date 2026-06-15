package com.ppcb.cafemerchant.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class UpdatableEntity {

    /**
     * The timestamp when the entity was last updated.
     * This field is automatically updated whenever the entity is modified.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updateAt;

    /**
     * The ID of the user who last updated the entity.
     * This field is automatically updated based on the currently authenticated user.
     */
    @LastModifiedBy
    @Column(name = "updated_by")
    private Integer updateBy;


}
