package com.ppcb.cafemerchant.common.domain.promotion;


import com.ppcb.cafemerchant.common.converter.IntegerListConverter;
import com.ppcb.cafemerchant.common.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "tb_promotions")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Promotion  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Integer promotionId;

    @Column(name = "promotion_name", length = 100, nullable = false)
    private String promotionName;

    @Column(name = "discount_type", length = 20, nullable = false)
    private String discountType;

    @Column(name = "discount_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal discountValue;

    @Convert(converter = IntegerListConverter.class)
    @Column(name = "applicable_items")
    private List<Integer> applicableItems;

    @CreationTimestamp
    @Column(updatable = true, name = "start_date")
    private Instant startDate;

    @CreationTimestamp
    @Column(updatable = true, name = "end_date")
    private Instant endDate;

    @Column(name = "max_total_uses", nullable = false)
    @ColumnDefault("0")
    private Integer maxTotalUses;

    @Column(name = "current_uses", nullable = false)
    @ColumnDefault("0")
    private Integer currentUses;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("true")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;



}
