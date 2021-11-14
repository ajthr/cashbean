package com.cashbean.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class LoginRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
