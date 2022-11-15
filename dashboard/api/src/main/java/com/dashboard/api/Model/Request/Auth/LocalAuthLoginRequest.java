package com.dashboard.api.Model.Request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalAuthLoginRequest {
    @NotBlank(message = "Please enter a valid username/email")
    String username;

    @NotBlank(message = "Please enter a password")
    String password;
}
