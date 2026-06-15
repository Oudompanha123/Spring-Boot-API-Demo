package com.ppcb.cafemerchant.common.domain.order;


import com.ppcb.cafemerchant.common.domain.item.ItemCustomize;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_order_item_customizations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class OrderItemCustomize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customization_id")
    private Integer customizationId;

    @ManyToOne
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private ItemCustomize itemCustomizationOption;

    @Column(name = "custom_value", length = 100)
    private String customValue;

    @Column(name = "additional_cost", precision = 10, scale = 2, nullable = false)
    @ColumnDefault("0.00")
    private BigDecimal additionalCost;
}
