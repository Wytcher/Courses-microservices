package br.com.coursesapi.respositories;

import br.com.coursesapi.entities.CourseEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {
    Optional<Page<CourseEnrollment>> findCourseEnrollmentByCourseId(Long id, Pageable pageable);
}
