package br.com.coursesapi.validators.CourseName;

import br.com.coursesapi.respositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueCourseNameValidator implements ConstraintValidator<UniqueCourseName, String> {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public boolean isValid(String courseName, ConstraintValidatorContext constraintValidatorContext) {
        return !courseRepository.existsByCourseName(courseName);
    }
}
