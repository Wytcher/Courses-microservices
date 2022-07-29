package br.com.authapi.dto.auth;

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
public class AuthRequestDTO {

    @ApiModelProperty(value = "email", name = "email", dataType = "string", example = "joaosilva@email.com", position = 1)
    @NotEmpty(message = "{required.email}")
    @Email(message = "{required.type.email}")
    private String email;

    @ApiModelProperty(value = "password", name = "password", dataType = "string", example = "12345678", position = 2)
    @NotEmpty(message = "{required.password}")
    private String password;
}
