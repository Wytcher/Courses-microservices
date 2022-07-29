package br.com.coursesapi.dto.CourseEnrollment;

import br.com.coursesapi.dto.course.CourseResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseEnrollmentResponseDTO {

    @ApiModelProperty(value = "id", name = "id", dataType = "Long", example = "1", position = 1)
    private Long id;

    @ApiModelProperty(value = "studentId", name = "studentId", dataType = "Long", example = "1", position = 1)
    private Long studentId;

    @ApiModelProperty(value = "studentName", name = "studentName", dataType = "String", example = "Gustavo Macena", position = 2)
    private String studentName;

    @ApiModelProperty(value = "courseId", name = "courseId", dataType = "Long", example = "1", position = 3)
    private CourseResponseDTO course;
}
