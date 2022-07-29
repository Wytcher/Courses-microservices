package br.com.authapi.dto.employee;

import br.com.authapi.dto.UserCreateRequestDTO;
import br.com.authapi.dto.student.StudentCreateAddressRequestDTO;
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
public class EmployeeCreateRequestDTO {

    @ApiModelProperty(value = "user", name = "user", dataType = "json", position = 1)
    @Valid
    private UserCreateRequestDTO user;

    @ApiModelProperty(value = "employeeBirthDate", name = "employeeBirthDate", dataType = "string", example = "1998-02-12", position = 3)
    @NotNull(message = "{required.birthdate}")
    private LocalDate employeeBirthDate;

    @ApiModelProperty(value = "employeeCpf", name = "employeeCpf", dataType = "string", example = "12312312312312", position = 4)
    @NotEmpty(message = "{required.cpf}")
    private String employeeCpf;

    @ApiModelProperty(value = "employeeRg", name = "employeeRg", dataType = "string", example = "123211232", position = 5)
    @NotEmpty(message = "{required.rg}")
    private String employeeRg;

    @ApiModelProperty(value = "employeePhone", name = "employeePhone", dataType = "string", example = "1112343212", position = 6)
    @NotEmpty(message = "{required.phone}")
    private String employeePhone;

    @ApiModelProperty(value = "employeeWorkCardNumber", name = "employeeWorkCardNumber", dataType = "string", example = "98762537462534", position = 7)
    @NotEmpty(message = "{required.work.card}")
    private String employeeWorkCardNumber;
}
