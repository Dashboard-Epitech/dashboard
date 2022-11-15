package com.dashboard.api.Model.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalAuthRegisterRequest {
    @NotBlank(message = "Please enter an username.")
    String username;

    @NotBlank(message = "Please enter your email.")
    @Email(message = "Invalid email address.")
    String email;

    @NotBlank(message = "Please enter a password.")
    String password;
}
