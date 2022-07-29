package br.com.coursesapi.respositories;

import br.com.coursesapi.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findCourseByEmployeeId(Long id);

    Boolean existsByCourseName(String courseName);
}
