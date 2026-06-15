package com.ppcb.cafemerchant.common.domain.category;


import com.ppcb.cafemerchant.common.domain.BaseEntity;
import com.ppcb.cafemerchant.common.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tb_categories")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name", length = 50, nullable = false)
    private String categoryName;

    @Column(name = "display_order", nullable = false)
    @ColumnDefault("0")
    private Integer displayOrder; // DEFAULT 0

    @Column(name = "icon_url", length = 255)
    private String iconUrl;

    @Column(name = "icon_emoji", length = 10)
    private String iconEmoji;

    @Column(name = "background_color", length = 7) // e.g., #RRGGBB
    private String backgroundColor;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("true")
    private Boolean isActive; // DEFAULT true

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
}
