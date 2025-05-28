package com.ss14.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotEmpty(message = "{error.username.required}")
    private String username;

    @Size(min = 6, message = "{error.password.length}")
    private String password;

    @NotEmpty(message = "{error.confirmPassword.required}")
    private String confirmPassword;

    @Email(message = "{error.email.invalid}")
    @NotEmpty(message = "{error.email.required}")
    private String email;

}
