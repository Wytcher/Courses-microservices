package br.com.coursesapi.dto.courseCategory;

import br.com.coursesapi.validators.CourseCategoryName.UniqueCourseCategoryName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseCategoryCreateRequestDTO {

    @ApiModelProperty(value = "categoryName", name = "categoryName", dataType = "string", example = "Java", position = 1)
    @NotEmpty(message = "{required.categoryName}")
    @UniqueCourseCategoryName(message = "{unique.categoryName}")
    private String categoryName;
}
