package com.ppcb.cafemerchant.common.components.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ApiCommon {

    @JsonProperty("api_id")
    private String xApiId;

    @JsonProperty("request_id")
    private String xRequestId;
}
