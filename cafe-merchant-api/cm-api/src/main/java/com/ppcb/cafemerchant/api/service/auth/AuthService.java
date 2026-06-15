package com.ppcb.cafemerchant.api.service.auth;

import com.ppcb.cafemerchant.api.payload.auth.LinkLoginRequest;
import com.ppcb.cafemerchant.api.payload.auth.LoginRequest;
import com.ppcb.cafemerchant.api.payload.auth.LoginResponse;
import jakarta.validation.Valid;

public interface AuthService {
    LoginResponse login(LoginRequest payload) throws Throwable;

    LoginResponse linkLogin(@Valid LinkLoginRequest payload) throws Throwable;
}
