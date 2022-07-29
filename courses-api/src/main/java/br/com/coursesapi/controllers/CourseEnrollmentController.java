package br.com.coursesapi.controllers;

import br.com.coursesapi.dto.CourseEnrollment.CourseEnrollmentCreateRequestDTO;
import br.com.coursesapi.dto.CourseEnrollment.CourseEnrollmentResponseDTO;
import br.com.coursesapi.entities.CourseEnrollment;
import br.com.coursesapi.services.CourseEnrollment.CourseEnrollmentServiceImpl;
import br.com.coursesapi.services.course.CourseServiceImpl;
import br.com.coursesapi.utils.MapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/courses/enrollments")
public class CourseEnrollmentController {

    private final MapperUtils mapperUtils = new MapperUtils();
    private final CourseEnrollmentServiceImpl courseEnrollmentService;
    private final CourseServiceImpl courseService;

    public CourseEnrollmentController(CourseEnrollmentServiceImpl courseEnrollmentService, CourseServiceImpl courseService) {
        this.courseEnrollmentService = courseEnrollmentService;
        this.courseService = courseService;
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping(value = "/course/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CourseEnrollmentResponseDTO>> findCourseEnrollments(@PathVariable Long id, Pageable pageable) {
        courseService.findCourseById(id);

        var pageableCourseEnrollments = courseEnrollmentService.findCourseEnrollmentsByCourseId(id, pageable);

        var pageableCourseEnrollmentsResponseDTO = mapperUtils.mapEntityPageIntoDtoPage(pageableCourseEnrollments, CourseEnrollmentResponseDTO.class);

        return ResponseEntity.ok(pageableCourseEnrollmentsResponseDTO);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseEnrollmentResponseDTO> createCourseEnrollment(@Validated @RequestBody CourseEnrollmentCreateRequestDTO courseEnrollmentCreateRequestDTO) {
        var course = courseService.findCourseById(courseEnrollmentCreateRequestDTO.getCourseId());

        var courseEnrollmentEntity = mapperUtils.map(courseEnrollmentCreateRequestDTO, CourseEnrollment.class);
        courseEnrollmentEntity.setCourse(course);
        var createdCourseEnrollment = courseEnrollmentService.createCourseEnrollment(courseEnrollmentEntity);

        var courseEnrollmentResponseDTO = mapperUtils.map(createdCourseEnrollment, CourseEnrollmentResponseDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(courseEnrollmentResponseDTO);
    }
}
