package com.ppcb.cafemerchant.api.payload.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record LinkLoginRequest(
        @NotBlank
        @JsonProperty("login_link_url")
        String loginLinkUrl
) {
}
