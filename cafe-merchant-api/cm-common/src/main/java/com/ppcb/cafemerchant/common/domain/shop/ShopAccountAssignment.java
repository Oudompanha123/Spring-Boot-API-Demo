package com.ppcb.cafemerchant.common.domain.shop;


import com.ppcb.cafemerchant.common.domain.account.Account;
import com.ppcb.cafemerchant.common.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;

@Entity
@Table(name = "tb_shop_account_assignments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class ShopAccountAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Integer assignmentId;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shopInfo;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @CreationTimestamp
    @Column(updatable = false, name = "assigned_at")
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "assigned_by")
    private User assignedBy;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("true")              // DEFAULT true
    private Boolean isActive;

}

