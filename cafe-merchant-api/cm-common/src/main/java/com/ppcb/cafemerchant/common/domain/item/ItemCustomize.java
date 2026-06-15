package com.ppcb.cafemerchant.common.domain.item;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_item_customization_options")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class ItemCustomize  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Integer optionId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private MenuItem menuItem;

    @Column(name = "option_type", length = 50, nullable = false)
    private String optionType;

    @Column(name = "option_name", length = 50, nullable = false)
    private String optionName;

    @Column(name = "price_modifier", precision = 10, scale = 2, nullable = false)
    @ColumnDefault("0.00")
    private BigDecimal priceModifier; // DEFAULT 0.00

    @Column(name = "is_default", nullable = false)
    @ColumnDefault("false")
    private Boolean isDefault; // DEFAULT false

    @Column(name = "display_order", nullable = false)
    @ColumnDefault("0")
    private Integer displayOrder; // DEFAULT 0

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("true")
    private Boolean isActive; // DEFAULT true
}
