package com.ss11.dto;

import com.ss11.validator.email.CustomEmail;
import com.ss11.validator.password.CustomPassword;
import com.ss11.validator.phone.CustomPhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotBlank(message = "Admin: Name bắt buộc nhập", groups = AdminGroup.class)
    @NotBlank(message = "User: Name bắt buộc nhập", groups = UserGroup.class)
    @Size(min = 2, max = 30, message = "Admin: Name phải từ 2 đến 30 ký tự", groups = AdminGroup.class)
    @Size(min = 2, max = 30, message = "User: Name phải từ 2 đến 30 ký tự", groups = UserGroup.class)
    private String name;

    @NotBlank(message = "Admin: Email bắt buộc nhập", groups = AdminGroup.class)
    @NotBlank(message = "User: Email bắt buộc nhập", groups = UserGroup.class)
    @Email(message = "Admin: Email không hợp lệ", groups = AdminGroup.class)
    @Email(message = "User: Email không hợp lệ", groups = UserGroup.class)
    @CustomEmail(message = "Admin: Email tùy chỉnh không hợp lệ", groups = AdminGroup.class)
    @CustomEmail(message = "User: Email tùy chỉnh không hợp lệ", groups = UserGroup.class)
    private String email;

    @NotBlank(message = "User: Phone bắt buộc nhập", groups = UserGroup.class)
    @CustomPhone(message = "User: Phone không hợp lệ phải bắt đầu bằng 0 và 9 số", groups = UserGroup.class)
    @Size(min = 10, message = "User: Phone phải 10 ký tự", groups = UserGroup.class)
    private String phone;

    @NotBlank(message = "Admin: Password bắt buộc nhập", groups = AdminGroup.class)
    @NotBlank(message = "User: Password bắt buộc nhập", groups = UserGroup.class)
    @Size(min = 8, message = "Admin: Password tối thiểu 8 ký tự", groups = AdminGroup.class)
    @Size(min = 8, message = "User: Password tối thiểu 8 ký tự", groups = UserGroup.class)
    @CustomPassword(message = "Admin: Password không hợp lệ", groups = AdminGroup.class)
    @CustomPassword(message = "User: Password không hợp lệ", groups = UserGroup.class)
    private String password;


    @NotNull(message = "Status can't be empty", groups = {AdminGroup.class, UserGroup.class})
    private Boolean status;

    @NotBlank(message = "Role is required", groups = {AdminGroup.class, UserGroup.class})
    private String role;
}
