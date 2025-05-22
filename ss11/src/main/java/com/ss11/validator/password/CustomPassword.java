package com.ss11.validator.password;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Constraint(validatedBy = CustomPasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CustomPasswords.class)
public @interface CustomPassword {
    String message() default "Invalid password (must contain at least 8 characters, at least one uppercase letter, at least one lowercase letter, at least one digit, at least one special character)";

    Class<?>[] groups() default {};

    Class<? extends javax.validation.Payload>[] payload() default {};
}
