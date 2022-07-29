package br.com.authapi.dto.student;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {

    @ApiModelProperty(value = "userId", name = "userId", dataType = "long", example = "1" ,position = 1)
    private Long userId;

    @ApiModelProperty(value = "studentId", name = "studentId", dataType = "long", example = "1" ,position = 2)
    private Long studentId;

    @ApiModelProperty(value = "name", name = "name", dataType = "string", example = "João Silva" ,position = 3)
    private String name;

    @ApiModelProperty(value = "studentBirthDate", name = "studentBirthDate", dataType = "string", example = "1998-02-12" ,position = 4)
    private LocalDate studentBirthDate;

}
