package com.ppcb.cafemerchant.api.payload.auth;

import com.ppcb.cafemerchant.common.security.SecurityUser;

public record AuthSuccessEvent(SecurityUser securityUser) { }
