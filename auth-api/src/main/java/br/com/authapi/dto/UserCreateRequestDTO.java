package br.com.authapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDTO {

    @ApiModelProperty(value = "firstName", name = "firstName", dataType = "string", example = "João", position = 1)
    @NotEmpty(message = "{required.first.name}")
    private String firstName;

    @ApiModelProperty(value = "lastName", name = "lastName", dataType = "string", example = "Silva", position = 2)
    @NotEmpty(message = "{required.last.name}")
    private String lastName;

    @ApiModelProperty(value = "email", name = "email", dataType = "string", example = "joaosilva@email.com", position = 3)
    @NotEmpty(message = "{required.email}")
    @Email(message = "{required.type.email}")
    private String email;

    @ApiModelProperty(value = "password", name = "password", dataType = "string", example = "12345678", position = 4)
    @NotEmpty(message = "{required.password}")
    private String password;
}
