package com.ppcb.cafemerchant.demo.controller;

import com.ppcb.cafemerchant.demo.payload.greeting.GreetingResponse;
import com.ppcb.cafemerchant.demo.service.greeting.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo/greeting")
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingService greetingService;

    @GetMapping("")
    public GreetingResponse greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name
    ) {
        return greetingService.greet(name);
    }
}
