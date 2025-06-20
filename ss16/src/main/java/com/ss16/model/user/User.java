package com.ss16.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private Long id;
    private String username;
    private String password;
    private String email;
    private UserRole role;
    private UserStatus status;
}
