package com.ss14.dto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueProductNameInCookieValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueProductNameInCookie{
    String message() default "Product name must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
