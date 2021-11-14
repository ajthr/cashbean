package com.cashbean.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class SignupRequest {

    @NotNull
    private final String email;

    @NotNull
    private final String name;

    @NotNull
    private final String password;

}
