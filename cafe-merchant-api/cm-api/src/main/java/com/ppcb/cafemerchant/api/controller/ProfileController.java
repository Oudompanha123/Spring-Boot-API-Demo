package com.ppcb.cafemerchant.api.controller;

import com.ppcb.cafemerchant.api.service.profile.ProfileService;
import com.ppcb.cafemerchant.common.components.api.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/ca/profile")
@RequiredArgsConstructor
public class ProfileController extends BaseController {

    private final ProfileService profileService;
    @GetMapping("")
    public Object getProfileInfo() {
        System.out.println("Get Profile ");
        return ok(profileService.getProfileInfo());
    }
}
