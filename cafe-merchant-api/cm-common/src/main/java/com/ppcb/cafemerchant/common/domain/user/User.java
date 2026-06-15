package com.ppcb.cafemerchant.common.domain.user;

import com.ppcb.cafemerchant.common.domain.BaseEntity;
import com.ppcb.cafemerchant.common.domain.company.Company;
import com.ppcb.cafemerchant.common.enums.LoginType;
import com.ppcb.cafemerchant.common.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;


@Entity
@Table(name = "tb_users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "user_type", length = 20, nullable = false)
    @Convert(converter = UserType.Converter.class)
    @ColumnDefault("'staff'")
    private UserType userType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_user_id")
    private User parentUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("true")
    private Boolean isActive;

    @Column(name = "password_hash", columnDefinition = "TEXT", nullable = false)
    private String passwordHash;

    @Column(name = "login_type", length = 20)
    @ColumnDefault("'link'")
    @Convert(converter = LoginType.Converter.class)
    private LoginType loginType ;

    @Column(name = "login_link_url", length = 500)
    private String loginLinkUrl;

    @Column(name = "last_login")
    private Instant lastLogin;

    @Column(name = "failed_login_attempts", nullable = false)
    @ColumnDefault("0")
    private Integer failedLoginAttempts;

    @CreationTimestamp
    @Column(name = "account_locked_until")
    private Instant accountLockedUntil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "managed_by")
    private Integer managedBy;
}