package com.ppcb.cafemerchant.common.domain.item;


import com.ppcb.cafemerchant.common.domain.BaseEntity;
import com.ppcb.cafemerchant.common.domain.category.Category;
import com.ppcb.cafemerchant.common.domain.user.User;
import com.ppcb.cafemerchant.common.enums.Currency;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_menu_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class MenuItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Foreign Key to Categories

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "sale_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal salePrice;

    @Column(name = "discount_price", precision = 10, scale = 2)
    private BigDecimal discountPrice;

    @Column(name = "base_cost", precision = 10, scale = 2)
    private BigDecimal baseCost;

    @Column(name = "currency", length = 3, nullable = false)
    @Convert(converter = Currency.Converter.class)
    @ColumnDefault("'USD'")
    private Currency currency; // DEFAULT 'USD'

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "item_color", length = 7) // e.g., #RRGGBB
    private String itemColor;

    @Column(name = "is_customizable", nullable = false)
    @ColumnDefault("false")
    private Boolean isCustomizable; // DEFAULT false

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("true")
    private Boolean isActive; // DEFAULT true

    @Column(name = "display_order", nullable = false)
    @ColumnDefault("0")
    private Integer displayOrder; // DEFAULT 0

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
}
