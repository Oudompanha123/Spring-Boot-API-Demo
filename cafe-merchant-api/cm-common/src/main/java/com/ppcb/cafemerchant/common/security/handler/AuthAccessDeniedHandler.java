package com.ppcb.cafemerchant.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppcb.cafemerchant.common.components.api.ApiCommon;
import com.ppcb.cafemerchant.common.components.api.ApiResponse;
import com.ppcb.cafemerchant.common.components.api.ApiStatus;
import com.ppcb.cafemerchant.common.components.api.EmptyJsonResponse;
import com.ppcb.cafemerchant.common.components.constant.StatusCode;
import com.ppcb.cafemerchant.common.helper.HeaderContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        try (ServletServerHttpResponse res = new ServletServerHttpResponse(response)) {
            res.setStatusCode(HttpStatus.FORBIDDEN);
            res.getServletResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            ApiStatus apiStatus = new ApiStatus(StatusCode.FORBIDDEN);
            var apiResponse = new ApiResponse<>(apiStatus, new EmptyJsonResponse());
            apiResponse.setCommon(
                    ApiCommon.builder()
                            .xApiId(HeaderContext.getCurrentApiId())
                            .xRequestId(HeaderContext.getCurrentRequestId())
                            .build()
            );

            res.getBody().write(objectMapper.writeValueAsString(apiResponse).getBytes());
        }
    }
}
