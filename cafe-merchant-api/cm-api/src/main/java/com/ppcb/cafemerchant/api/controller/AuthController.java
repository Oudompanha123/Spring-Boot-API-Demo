package com.ppcb.cafemerchant.api.controller;

import com.ppcb.cafemerchant.api.payload.auth.LinkLoginRequest;
import com.ppcb.cafemerchant.api.payload.auth.LoginRequest;
import com.ppcb.cafemerchant.api.service.auth.AuthService;
import com.ppcb.cafemerchant.common.components.api.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ca/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest payload) throws Throwable {
        return ok(authService.login(payload));
    }

    @PostMapping("/link")
    public ResponseEntity<?> linkLogin(@RequestBody @Valid LinkLoginRequest payload) throws Throwable {
        return ok(authService.linkLogin(payload));
    }

}
