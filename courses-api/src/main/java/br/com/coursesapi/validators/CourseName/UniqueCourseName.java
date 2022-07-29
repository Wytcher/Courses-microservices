package br.com.coursesapi.validators.CourseName;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCourseNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCourseName {

    String message() default "This course name already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
