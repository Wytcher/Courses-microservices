package br.com.coursesapi.services.courseCategory;

import br.com.coursesapi.entities.CourseCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseCategoryService {

    Page<CourseCategory> findAllCourseCategories(Pageable pageable);

    CourseCategory findCourseCategoryById(Long id);

    CourseCategory findCourseCategoryByName(String categoryName);

    CourseCategory saveCourseCategory(CourseCategory courseCategory);
}
