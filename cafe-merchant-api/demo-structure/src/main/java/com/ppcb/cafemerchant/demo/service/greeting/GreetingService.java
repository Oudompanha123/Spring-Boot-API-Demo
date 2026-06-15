package com.ppcb.cafemerchant.demo.service.greeting;

import com.ppcb.cafemerchant.demo.payload.greeting.GreetingResponse;

public interface GreetingService {
    GreetingResponse greet(String name);
}
