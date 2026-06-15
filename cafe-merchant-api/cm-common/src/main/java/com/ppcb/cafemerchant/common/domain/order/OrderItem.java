package com.ppcb.cafemerchant.common.domain.order;


import com.ppcb.cafemerchant.common.domain.item.MenuItem;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_order_items")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "vat_code",length = 10,nullable = false)
    private String vatCode;

    @Column(name = "vat_value",length = 3, nullable = false)
    private String vatValue;

    @Column(name = "quantity", nullable = false)
    @ColumnDefault("1")
    private Integer quantity; // DEFAULT 1

    @Column(name = "original_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal originalPrice;

    @Column(name = "unit_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "discount_rate", length = 3, nullable = false)
    private String discountRate;

    @Column(name = "discount_amount", precision = 10, scale = 2, nullable = false)
    @ColumnDefault("0.00")
    private BigDecimal discountAmount;

    @Column(name = "total_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "special_notes", columnDefinition = "TEXT")
    private String specialNotes;

}
