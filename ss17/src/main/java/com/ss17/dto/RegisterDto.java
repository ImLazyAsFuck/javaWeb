package com.ss17.dto;

import com.ss17.validator.unique.IsUsernameExist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto{

    @NotBlank(message = "Username cannot be empty")
    @IsUsernameExist
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email (example: example@domain.com)")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Invalid Viet Nam phone number")
    private String phone;
}
