package br.com.coursesapi.services.course;

import br.com.coursesapi.entities.Course;
import br.com.coursesapi.exceptions.business.ObjectNotFoundException;
import br.com.coursesapi.respositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<Course> findAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Course for the given id %s was not found", id)));
    }

    @Override
    public Course findCourseByEmployee(Long employeeId) {
        return courseRepository
                .findCourseByEmployeeId(employeeId)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Course for the given employee id %s was not found", employeeId)));
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
}
