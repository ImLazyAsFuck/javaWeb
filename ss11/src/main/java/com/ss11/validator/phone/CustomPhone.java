package com.ss11.validator.phone;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Constraint(validatedBy = CustomPhoneValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CustomPhones.class)
public @interface CustomPhone{
    String message() default "Invalid phone (0xxxxxxxxxx)";
    Class<?>[] groups() default {};
    Class<? extends javax.validation.Payload>[] payload() default {};
}
