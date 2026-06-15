package com.ppcb.cafemerchant.common.components.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ppcb.cafemerchant.common.components.constant.StatusCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude
public class ApiResponse<T> {

    @JsonProperty("common")
    private ApiCommon common;

    @JsonProperty("status")
    private ApiStatus statusCode;

    @JsonProperty("data")
    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(ApiStatus status, T data) {
        this.statusCode = status;
        this.data = data;
    }

    @Builder
    public ApiResponse(StatusCode status, T data, ApiCommon common) {
        this.statusCode = new ApiStatus(status);
        this.data = data;
        this.common = common;
    }

    @Builder
    public ApiResponse(int code, String message, T data) {
        this.statusCode = new ApiStatus(code, message);
        this.data = data;
    }
}
