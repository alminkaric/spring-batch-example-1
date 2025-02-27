package com.techprimers.springbatchexample1.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * TODO: Add javadoc
 * @author akaric
 *
 */
@Documented
@Constraint(validatedBy = DepartmantFormatValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DepartmantValidation {
    String message() default "Invalid departmant format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}