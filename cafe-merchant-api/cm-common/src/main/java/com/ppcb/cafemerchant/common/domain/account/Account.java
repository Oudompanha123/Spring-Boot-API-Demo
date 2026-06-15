package com.ppcb.cafemerchant.common.domain.account;


import com.ppcb.cafemerchant.common.domain.BaseEntity;
import com.ppcb.cafemerchant.common.domain.user.User;
import com.ppcb.cafemerchant.common.enums.Currency;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tb_account_info")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @ManyToOne
    @JoinColumn(name = "owner_user_id", nullable = false)
    private User ownerUser;

    @Column(name = "account_number", length = 50)
    private String accountNumber;

    @Column(name = "account_holder_name", length = 100)
    private String accountHolderName;

    @Column(name = "bank_code", length = 10, nullable = false)
    @ColumnDefault("'PPCB'")
    private String bankCode;

    @Column(name = "bank_short_name", length = 50, nullable = false)
    @ColumnDefault("'PPCBank'")
    private String bankShortName;

    @Column(name = "bank_name", length = 100, nullable = false)
    @ColumnDefault("'Phnom Penh Commercial Bank'")
    private String bankName;

    @Column(name = "currency", length = 3, nullable = false)
    @ColumnDefault("'USD'")
    @Convert(converter = Currency.Converter.class)
    private Currency currency;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("true")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by") // Can be nullable as per schema
    private User updatedBy;
}
