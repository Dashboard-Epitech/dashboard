package com.dashboard.api.Auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    @NotNull
    @NotBlank(message = "Please enter an username.")
    private String username;

    @NotNull
    @NotBlank(message = "Please enter your password.")
    private String password;
}
