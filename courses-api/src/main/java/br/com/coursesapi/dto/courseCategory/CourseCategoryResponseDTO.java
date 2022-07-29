package br.com.coursesapi.dto.courseCategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseCategoryResponseDTO {

    @ApiModelProperty(value = "id", name = "id", dataType = "Long", example = "1", position = 1)
    private Long id;

    @ApiModelProperty(value = "categoryName", name = "categoryName", dataType = "string", example = "Java", position = 2)
    private String categoryName;
}
