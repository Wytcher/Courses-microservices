package br.com.coursesapi.dto.CourseEnrollment;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseEnrollmentCreateRequestDTO {

    @ApiModelProperty(value = "studentId", name = "studentId", dataType = "Long", example = "1", position = 1)
    @NotNull(message = "{required.studentId}")
    private Long studentId;

    @ApiModelProperty(value = "studentName", name = "studentName", dataType = "String", example = "Gustavo Macena", position = 2)
    @NotNull(message = "{required.studentName}")
    private String studentName;

    @ApiModelProperty(value = "courseId", name = "courseId", dataType = "Long", example = "1", position = 3)
    @NotNull(message = "{required.courseId}")
    private Long courseId;
}
