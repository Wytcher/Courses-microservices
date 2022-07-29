package br.com.coursesapi.services.CourseEnrollment;

import br.com.coursesapi.entities.CourseEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseEnrollmentService {

    Page<CourseEnrollment> findCourseEnrollmentsByCourseId(Long courseId, Pageable pageable);

    CourseEnrollment findCourseEnrollmentById(Long id);

    CourseEnrollment saveCourseEnrollment(CourseEnrollment courseEnrollment);
}
