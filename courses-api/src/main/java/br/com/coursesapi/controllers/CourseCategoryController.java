package br.com.coursesapi.controllers;

import br.com.coursesapi.dto.courseCategory.CourseCategoryCreateRequestDTO;
import br.com.coursesapi.dto.courseCategory.CourseCategoryResponseDTO;
import br.com.coursesapi.entities.CourseCategory;
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
@RequestMapping("api/v1/courses/categories")
public class CourseCategoryController {

    private final MapperUtils mapperUtils = new MapperUtils();
    private final CourseCategoryServiceImpl courseCategoryService;

    public CourseCategoryController(CourseCategoryServiceImpl courseCategoryService) {
        this.courseCategoryService = courseCategoryService;
    }

    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CourseCategoryResponseDTO>> findAllCourseCategories(Pageable pageable) {
        var pageableCourseCategories = courseCategoryService.findAllCourseCategories(pageable);

        var pageableCourseCategoriesDTO = mapperUtils.mapEntityPageIntoDtoPage(pageableCourseCategories, CourseCategoryResponseDTO.class);

        return ResponseEntity.ok(pageableCourseCategoriesDTO);
    }

    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseCategoryResponseDTO> createCourseCategory(@Validated @RequestBody CourseCategoryCreateRequestDTO courseCategoryCreateRequestDTO) {
        var courseCategoryEntity = mapperUtils.map(courseCategoryCreateRequestDTO, CourseCategory.class);

        var createdCourseCategory = courseCategoryService.saveCourseCategory(courseCategoryEntity);

        var courseCategoryResponseDTO = mapperUtils.map(createdCourseCategory, CourseCategoryResponseDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(courseCategoryResponseDTO);
    }
}
