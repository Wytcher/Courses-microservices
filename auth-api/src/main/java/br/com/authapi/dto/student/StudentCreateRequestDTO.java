package br.com.authapi.dto.student;

import br.com.authapi.dto.UserCreateRequestDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequestDTO {

    @ApiModelProperty(value = "user", name = "user", dataType = "json", position = 1)
    @Valid
    private UserCreateRequestDTO user;

    @ApiModelProperty(value = "studentAddress", name = "studentAddress", dataType = "json", position = 2)
    @Valid
    private StudentCreateAddressRequestDTO studentAddress;

    @ApiModelProperty(value = "studentBirthDate", name = "studentBirthDate", dataType = "string", example = "1998-02-12", position = 3)
    @NotNull(message = "{required.birthdate}")
    private LocalDate studentBirthDate;

    @ApiModelProperty(value = "studentCpf", name = "studentCpf", dataType = "string", example = "12312312312312", position = 4)
    @NotEmpty(message = "{required.cpf}")
    private String studentCpf;

    @ApiModelProperty(value = "studentRg", name = "studentRg", dataType = "string", example = "123211232", position = 5)
    @NotEmpty(message = "{required.rg}")
    private String studentRg;

    @ApiModelProperty(value = "studentPhone", name = "studentPhone", dataType = "string", example = "1112343212", position = 6)
    @NotEmpty(message = "{required.phone}")
    private String studentPhone;

}
