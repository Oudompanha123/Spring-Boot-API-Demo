package com.ppcb.cafemerchant.common.domain.company;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_companies")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "company_name", length = 100, nullable = false)
    private String companyName;

    @Column(name = "registration_number", length = 50)
    private String registrationNumber;

    @Column(name = "tin", length = 50)
    private String tin;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Column(name = "website_url", length = 255)
    private String websiteUrl;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

}