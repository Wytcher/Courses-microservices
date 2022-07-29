package br.com.coursesapi.respositories;

import br.com.coursesapi.entities.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {

    Optional<CourseCategory> findByCategoryName(String categoryName);

    Boolean existsByCategoryName(String categoryName);
}
