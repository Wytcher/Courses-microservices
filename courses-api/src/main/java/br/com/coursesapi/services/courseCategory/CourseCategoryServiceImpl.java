package br.com.coursesapi.services.courseCategory;

import br.com.coursesapi.entities.CourseCategory;
import br.com.coursesapi.exceptions.business.ObjectNotFoundException;
import br.com.coursesapi.respositories.CourseCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CourseCategoryRepository courseCategoryRepository;

    public CourseCategoryServiceImpl(CourseCategoryRepository courseCategoryRepository) {
        this.courseCategoryRepository = courseCategoryRepository;
    }

    @Override
    public Page<CourseCategory> findAllCourseCategories(Pageable pageable) {
        return courseCategoryRepository.findAll(pageable);
    }

    @Override
    public CourseCategory findCourseCategoryById(Long id) {
        return courseCategoryRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Category for the given id %s was not found", id)));
    }

    @Override
    public CourseCategory findCourseCategoryByName(String categoryName) {
        return courseCategoryRepository
                .findByCategoryName(categoryName)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Category for the given name %s was not found", categoryName)));
    }

    @Override
    public CourseCategory saveCourseCategory(CourseCategory courseCategory) {
        return courseCategoryRepository.save(courseCategory);
    }
}
