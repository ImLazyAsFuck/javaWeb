package com.b10.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CvDto{
    private long id;

    @NotBlank(message = "Fullname is required")
    private String fullname;

    @NotBlank(message = "Email is required")
    @UniqueEmail
    @Email(message = "Email is invalid(example: a@domain.com)")
    private String email;

    @NotBlank(message = "Phone is required")
    @UniquePhone
    @Pattern(regexp = "^0(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])[0-9]{7}$", message = "Phone is invalid please enter Viet Name phone number")
    private String phone;

    @NotBlank(message = "Education is required")
    private String education;

    @NotBlank(message = "Experience is required")
    private String experience;

    @NotBlank(message = "Skills is required")
    private String skills;

    private MultipartFile file;

    private String image;
}
