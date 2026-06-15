package com.ppcb.cafemerchant.common.helper;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HeaderContext {
    private static final String REQUEST_HEADER_ATTRIBUTE = "requestHeader";

    private HeaderContext() {
        // Private constructor to prevent instantiation
    }

    /**
     * Gets the current request header from the request context
     * @return The request header or null if not available
     */
    public static RequestHeader getCurrentRequestHeader() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }

        HttpServletRequest request = attributes.getRequest();
        return (RequestHeader) request.getAttribute(REQUEST_HEADER_ATTRIBUTE);
    }

    /**
     * Gets the current API ID from request header
     * @return The API ID or "UNKNOWN" if not available
     */
    public static String getCurrentApiId() {
        RequestHeader header = getCurrentRequestHeader();
        return header != null ? header.getXApiId() : "UNKNOWN";
    }

    /**
     * Gets the current request ID from request header
     * @return The request ID or "UNKNOWN" if not available
     */
    public static String getCurrentRequestId() {
        RequestHeader header = getCurrentRequestHeader();
        return header != null ? header.getXRequestId() : "UNKNOWN";
    }
}
