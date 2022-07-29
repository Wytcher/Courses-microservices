package br.com.coursesapi.dto.course;

import br.com.coursesapi.dto.courseCategory.CourseCategoryResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {

    @ApiModelProperty(value = "id", name = "id", dataType = "long", example = "1", position = 1)
    private Long id;

    @ApiModelProperty(value = "employeeId", name = "employeeId", dataType = "lon", example = "1", position = 2)
    private Integer employeeId;

    @ApiModelProperty(value = "employeeName", name = "employeeName", dataType = "string", example = "João Silva", position = 3)
    private String employeeName;

    @ApiModelProperty(value = "courseName", name = "courseName", dataType = "string", example = "Java Developer", position = 4)
    private String courseName;

    @ApiModelProperty(value = "courseDuration", name = "courseDuration", dataType = "string", example = "500 Horas", position = 5)
    private String courseDuration;

    @ApiModelProperty(value = "courseCategory", name = "courseCategory", dataType = "json", position = 6)
    private CourseCategoryResponseDTO courseCategory;
}
