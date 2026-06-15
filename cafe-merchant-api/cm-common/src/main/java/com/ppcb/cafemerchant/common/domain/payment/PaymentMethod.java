package com.ppcb.cafemerchant.common.domain.payment;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tb_payment_methods")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "method_id")
    private Integer methodId;

    @Column(name = "method_name", length = 50, nullable = false)
    private String methodName;

    @Column(name = "method_code", length = 20, unique = true, nullable = false)
    private String methodCode;

    @Column(name = "method_type", length = 20, nullable = false)
    private String methodType;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("true")
    private Boolean isActive;

    @Column(name = "display_order", nullable = false)
    @ColumnDefault("0")
    private Integer displayOrder;

    @Column(name = "icon_url", length = 255)
    private String iconUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
