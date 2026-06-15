package com.ppcb.cafemerchant.common.domain.order;


import com.ppcb.cafemerchant.common.domain.BaseEntity;
import com.ppcb.cafemerchant.common.domain.user.User;
import com.ppcb.cafemerchant.common.enums.Currency;
import com.ppcb.cafemerchant.common.enums.OrderStatus;
import com.ppcb.cafemerchant.common.enums.OrderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_orders")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_number", length = 20, unique = true, nullable = false)
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "cashier_id", nullable = false)
    private User cashier;

    @Column(name = "order_status", length = 20, nullable = false)
    @ColumnDefault("'pending'")
    @Convert(converter = OrderStatus.Converter.class)
    private OrderStatus orderStatus;    // DEFAULT 'pending'

    @Column(name = "order_type", length = 20, nullable = false)
    @ColumnDefault("'pickup'")
    @Convert(converter = OrderType.Converter.class)
    private OrderType orderType;        // DEFAULT 'pickup'

    @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @Column(name = "tax_amount", precision = 10, scale = 2, nullable = false)
    @ColumnDefault("0.00")
    private BigDecimal taxAmount;       // DEFAULT 0.00

    @Column(name = "total_discount_amount", precision = 10, scale = 2, nullable = false)
    @ColumnDefault("0.00")
    private BigDecimal totalDiscountAmount;     // DEFAULT 0.00

    @Column(name = "total_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "currency", length = 3, nullable = false)
    @ColumnDefault("'USD'")
    @Convert(converter = Currency.Converter.class)
    private Currency currency;              // DEFAULT 'USD'

    @Column(name = "special_instructions", columnDefinition = "TEXT")
    private String specialInstructions;

}
