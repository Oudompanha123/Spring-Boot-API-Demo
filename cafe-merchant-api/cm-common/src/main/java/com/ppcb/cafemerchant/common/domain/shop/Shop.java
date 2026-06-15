package com.ppcb.cafemerchant.common.domain.shop;


import com.ppcb.cafemerchant.common.domain.BaseEntity;
import com.ppcb.cafemerchant.common.domain.company.Company;
import com.ppcb.cafemerchant.common.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tb_shop_info")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Shop extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Integer shopId;

    @ManyToOne
    @JoinColumn(name = "owner_user_id", nullable = false)
    private User ownerUser;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "shop_name", length = 100, nullable = false)
    private String shopName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "shop_url", length = 500)
    private String shopUrl;

    @Column(name = "thumbnail_url", length = 255)
    private String thumbnailUrl;

    @Column(name = "qr_code_data", columnDefinition = "TEXT")
    private String qrCodeData;

    @Column(name = "qr_code_image_url", length = 255)
    private String qrCodeImageUrl;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("true")                  // DEFAULT true
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")        // Can be nullable as per schema (no NOT NULL)
    private User updatedBy;
}
