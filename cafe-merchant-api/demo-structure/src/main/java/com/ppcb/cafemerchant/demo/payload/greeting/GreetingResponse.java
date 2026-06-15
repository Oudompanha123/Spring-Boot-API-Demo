package com.ppcb.cafemerchant.demo.payload.greeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GreetingResponse {

    @JsonProperty("message")
    private String message;
}
