package com.ppcb.cafemerchant.api.payload.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @NotBlank
        @Size(max = 50)
        String username,

        @NotBlank
        @Size(max = 150)
        String password
) {
}