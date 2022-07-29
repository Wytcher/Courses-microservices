package br.com.coursesapi.validators.CourseCategoryName;

import br.com.coursesapi.respositories.CourseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueCourseCategoryNameValidator implements ConstraintValidator<UniqueCourseCategoryName, String> {

    @Autowired
    CourseCategoryRepository courseCategoryRepository;

    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext constraintValidatorContext) {
        return !courseCategoryRepository.existsByCategoryName(categoryName);
    }
}
