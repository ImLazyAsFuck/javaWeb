package com.ss17.validator.uniqueproductname;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsProductNameExistValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsProductNameExist{
    String message() default "Product name already exist";
    Class<?>[] groups() default {};
    Class<? extends java.lang.annotation.Annotation>[] payload() default {};
}
