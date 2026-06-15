package com.ppcb.cafemerchant.common.helper;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestHeader {

    @Size(max = 10, message = "X-Api-Id must not exceed 10 characters")
    private String xApiId;

    @Size(max = 30, message = "X-Request-Id must not exceed 30 characters")
    private String xRequestId;

}
