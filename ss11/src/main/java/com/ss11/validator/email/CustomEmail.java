package com.ss11.validator.email;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = CustomEmailValidator.class)
@Target({ElementType.FIELD})
@Repeatable(CustomEmails.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomEmail{
    String message() default "Invalid email (email@domain.com)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
