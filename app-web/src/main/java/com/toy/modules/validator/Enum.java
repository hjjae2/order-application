package com.toy.modules.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {EnumValidator.class})
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface Enum {

    String message() default "{com.toy.assignment.modules.validator.NotBlankForEnum.message}";

    Class<? extends java.lang.Enum<?>> type();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
