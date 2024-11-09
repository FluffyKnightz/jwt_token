package com.test.jwt.dto;

import lombok.Builder;


@Builder
public record UserDto(String name, String email, String password) {

}
