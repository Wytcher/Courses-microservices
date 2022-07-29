package br.com.coursesapi.validators.CourseCategoryName;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCourseCategoryNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCourseCategoryName {

    String message() default "This category name already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
