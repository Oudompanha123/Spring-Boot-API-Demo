package com.ppcb.cafemerchant.common.domain.resource;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;

@Entity
@Table(name = "tb_resources")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL implies auto-incrementing integer primary key
    @Column(name = "resource_id")
    private Integer resourceId;

    @Column(name = "resource_name", length = 50, unique = true, nullable = false)
    private String resourceName;

    @Column(name = "resource_description", columnDefinition = "TEXT")
    private String resourceDescription;

    @Column(name = "resource_category", length = 50)
    private String resourceCategory;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("true")
    private Boolean isActive; // DEFAULT true

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Instant createdAt;
}
