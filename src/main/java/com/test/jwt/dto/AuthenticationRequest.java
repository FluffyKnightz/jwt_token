package com.test.jwt.dto;

import lombok.Builder;

@Builder
public record AuthenticationRequest(String name,
                                    String password,
                                    boolean rememberMe) {


}
