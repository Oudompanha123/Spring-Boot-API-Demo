package com.ppcb.cafemerchant.common.domain.payment;


import com.ppcb.cafemerchant.common.domain.order.Order;
import com.ppcb.cafemerchant.common.enums.Currency;
import com.ppcb.cafemerchant.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "tb_payments")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;


    @Column(name = "currency", length = 3, nullable = false)
    @ColumnDefault("'USD'")
    @Convert(converter = Currency.Converter.class)
    private Currency currency;

    @Column(name = "payment_status", length = 20, nullable = false)
    @ColumnDefault("'pending'")
    @Convert(converter = PaymentStatus.Converter.class)
    private PaymentStatus paymentStatus;

    @Column(name = "qr_code_data", columnDefinition = "TEXT")
    private String qrCodeData;

    @Column(name = "external_transaction_id", length = 100)
    private String externalTransactionId;

    @Column(name = "payment_notes", columnDefinition = "TEXT")
    private String paymentNotes;

    @CreationTimestamp
    @Column(name = "processed_at")
    private Instant processedAt;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Instant createdAt;

}
