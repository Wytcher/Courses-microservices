package br.com.coursesapi.controllers;

import br.com.coursesapi.dto.course.CourseCreateRequestDTO;
import br.com.coursesapi.dto.course.CourseResponseDTO;
import br.com.coursesapi.entities.Course;
import br.com.coursesapi.services.course.CourseServiceImpl;
import br.com.coursesapi.services.courseCategory.CourseCategoryServiceImpl;
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
@RequestMapping("api/v1/courses")
public class CourseController {

    private final MapperUtils mapperUtils = new MapperUtils();
    private final CourseServiceImpl courseService;
    private final CourseCategoryServiceImpl courseCategoryService;

    public CourseController(CourseServiceImpl courseService, CourseCategoryServiceImpl courseCategoryService) {
        this.courseService = courseService;
        this.courseCategoryService = courseCategoryService;
    }

    @PreAuthorize("hasAnyAuthority('STUDENT', 'EMPLOYEE', 'ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CourseResponseDTO>> findAllCourses(Pageable pageable) {
        var pageableCourses = courseService.findAllCourses(pageable);

        var pageableCoursesResponseDTO = mapperUtils.mapEntityPageIntoDtoPage(pageableCourses, CourseResponseDTO.class);

        return ResponseEntity.ok(pageableCoursesResponseDTO);
    }

    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseResponseDTO> createCourse(@Validated @RequestBody CourseCreateRequestDTO courseCreateRequestDTO) {
        var courseCategory = courseCategoryService.findCourseCategoryById(courseCreateRequestDTO.getCourseCategoryId());

        var courseEntity = mapperUtils.map(courseCreateRequestDTO, Course.class);
        courseEntity.setCourseCategory(courseCategory);
        var createdCourse = courseService.saveCourse(courseEntity);

        var courseResponseDTO = mapperUtils.map(createdCourse, CourseResponseDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(courseResponseDTO);
    }
}
