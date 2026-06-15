package com.ppcb.cafemerchant.common.domain.log;


import com.ppcb.cafemerchant.common.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_access_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_id")
    private Integer accessId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(name = "login_time", nullable = false)
    private Instant loginTime;

    @CreationTimestamp
    @Column(name = "logout_time")
    private Instant logoutTime;

    @Column(name = "ip_address", length = 100)
    private String ipAddress;

    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;

    @Column(name = "device_info", columnDefinition = "JSONB")
    private String deviceInfo;

    @Column(name = "login_method", length = 20, nullable = false)
    @ColumnDefault("'password'")
    private String loginMethod; // In-code default for consistency

    @Column(name = "login_status", length = 20, nullable = false)
    @ColumnDefault("'success'")
    private String loginStatus; // In-code default for consistency

    @Column(name = "failure_reason", length = 100)
    private String failureReason;

    @Column(name = "session_duration")
    private Integer sessionDuration;

    @Column(name = "pages_visited", nullable = false)
    @ColumnDefault("0")
    private Integer pagesVisited;

    @Column(name = "last_activity")
    private LocalDateTime lastActivity;
}
