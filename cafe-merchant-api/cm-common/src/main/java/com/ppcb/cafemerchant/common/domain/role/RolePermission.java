package com.ppcb.cafemerchant.common.domain.role;


import com.ppcb.cafemerchant.common.domain.permission.Permission;
import com.ppcb.cafemerchant.common.domain.resource.Resource;
import com.ppcb.cafemerchant.common.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tb_role_permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // SERIAL implies auto-incrementing integer primary key
    @Column(name = "role_permission_id")
    private Integer rolePermissionId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @Column(name = "is_granted", nullable = false)
    @ColumnDefault("true")
    private Boolean isGranted;              // DEFAULT true

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
}
