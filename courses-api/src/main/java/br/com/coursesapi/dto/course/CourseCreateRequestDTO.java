package br.com.coursesapi.dto.course;

import br.com.coursesapi.validators.CourseCategoryName.UniqueCourseCategoryName;
import br.com.coursesapi.validators.CourseName.UniqueCourseName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateRequestDTO {

    @ApiModelProperty(value = "courseName", name = "courseName", dataType = "string", example = "Java Developer", position = 1)
    @NotEmpty(message = "{required.courseName}")
    @UniqueCourseName(message = "{unique.courseName}")
    private String courseName;

    @ApiModelProperty(value = "employeeId", name = "employeeId", dataType = "Long", example = "1", position = 2)
    @NotNull(message = "{required.employeeId}")
    private Long employeeId;

    @ApiModelProperty(value = "employeeName", name = "employeeName", dataType = "string", example = "João Silva", position = 3)
    @NotEmpty(message = "{required.employeeName}")
    private String employeeName;

    @ApiModelProperty(value = "courseDuration", name = "courseDuration", dataType = "string", example = "500 Horas", position = 4)
    @NotEmpty(message = "{required.courseDuration}")
    private String courseDuration;

    @ApiModelProperty(value = "courseCategoryId", name = "courseCategoryId", dataType = "lONG", example = "1", position = 5)
    @NotNull(message = "{required.courseCategoryId}")
    private Long courseCategoryId;
}
