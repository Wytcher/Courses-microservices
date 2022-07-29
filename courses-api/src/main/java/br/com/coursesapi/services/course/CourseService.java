package br.com.coursesapi.services.course;

import br.com.coursesapi.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Page<Course> findAllCourses(Pageable pageable);

    Course findCourseById(Long id);

    Course findCourseByEmployee(Long employeeId);

    Course saveCourse(Course course);
}
