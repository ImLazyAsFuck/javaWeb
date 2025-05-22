package com.ss11.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = PastOrPresentDateValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PastOrPresentDate {
    String message() default "Ngày phải là ngày hiện tại hoặc trong quá khứ!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
