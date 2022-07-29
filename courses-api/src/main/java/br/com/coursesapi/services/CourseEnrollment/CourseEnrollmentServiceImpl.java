package br.com.coursesapi.services.CourseEnrollment;

import br.com.coursesapi.entities.CourseEnrollment;
import br.com.coursesapi.entities.EnrollmentStatus;
import br.com.coursesapi.exceptions.business.ObjectNotFoundException;
import br.com.coursesapi.respositories.CourseEnrollmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

    private final CourseEnrollmentRepository courseEnrollmentRepository;

    public CourseEnrollmentServiceImpl(CourseEnrollmentRepository courseEnrollmentRepository) {
        this.courseEnrollmentRepository = courseEnrollmentRepository;
    }

    @Override
    public Page<CourseEnrollment> findCourseEnrollmentsByCourseId(Long courseId, Pageable pageable) {
        return courseEnrollmentRepository
                .findCourseEnrollmentByCourseId(courseId, pageable)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Enrollments for the given course ID %s was not found", courseId)));
    }

    @Override
    public CourseEnrollment findCourseEnrollmentById(Long id) {
        return courseEnrollmentRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Enrollment for the given ID %s was not found", id)));
    }

    @Override
    public CourseEnrollment saveCourseEnrollment(CourseEnrollment courseEnrollment) {
        return courseEnrollmentRepository.save(courseEnrollment);
    }

    public CourseEnrollment createCourseEnrollment(CourseEnrollment courseEnrollmentEntity) {
        courseEnrollmentEntity.setEnrollmentStatus(EnrollmentStatus.ACTIVE);

        return saveCourseEnrollment(courseEnrollmentEntity);
    }
}
