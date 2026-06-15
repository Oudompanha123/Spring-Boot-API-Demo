package com.ppcb.cafemerchant.demo.service.greeting;

import com.ppcb.cafemerchant.demo.payload.greeting.GreetingResponse;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    public GreetingResponse greet(String name) {
        return GreetingResponse.builder()
                .message("Hello, " + name + "!")
                .build();
    }
}
