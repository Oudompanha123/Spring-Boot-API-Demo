package com.ppcb.cafemerchant.api.payload.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProfileResponse {
    @JsonProperty(value = "full_name", required = true)
    private String fullName;
    @JsonProperty(value = "profile_picture", required = true)
    private String profileImage;
    @JsonProperty(value = "phone_number", required = true)
    private String phoneNumber;
    @JsonProperty(value = "company_number", required = true)
    private String companyName;
    @JsonProperty(value = "email", required = true)
    private String email;
    @JsonProperty(value = "address", required = true)
    private String address;
    @JsonProperty(value = "role_name", required = true)
    private String roleName;
    @JsonProperty(value = "role_id", required = true)
    private Long roleId;

    @Builder
    public ProfileResponse(String fullName, String profileImage, String phoneNumber, String companyName, String email, String address, String roleName, Long roleId) {
        this.fullName = fullName;
        this.profileImage = profileImage;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.email = email;
        this.address = address;
        this.roleName = roleName;
        this.roleId = roleId;
    }
}
