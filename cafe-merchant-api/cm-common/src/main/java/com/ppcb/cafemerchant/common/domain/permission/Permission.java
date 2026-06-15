package com.ppcb.cafemerchant.common.domain.permission;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;

@Entity
@Table(name = "tb_permissions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // SERIAL implies auto-incrementing integer primary key
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "permission_name", length = 50, nullable = false)
    private String permissionName;

    @Column(name = "permission_description", columnDefinition = "TEXT")
    private String permissionDescription;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("true")
    private Boolean isActive;                            // DEFAULT true

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Instant createdAt;
}
