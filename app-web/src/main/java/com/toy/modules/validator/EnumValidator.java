package com.toy.modules.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class EnumValidator implements ConstraintValidator<Enum, java.lang.Enum<?>> {

    private Class<? extends java.lang.Enum<?>> enumType;

    @Override
    public void initialize(Enum constraintAnnotation) {
        enumType = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(java.lang.Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        java.lang.Enum<?>[] enumConstants = this.enumType.getEnumConstants();

        for (java.lang.Enum<?> enumConstant : enumConstants) {
            if (value == enumConstant) {
                return true;
            }
        }

        return false;
    }
}
