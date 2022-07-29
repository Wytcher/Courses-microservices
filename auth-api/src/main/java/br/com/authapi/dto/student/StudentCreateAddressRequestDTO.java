package br.com.authapi.dto.student;

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
public class StudentCreateAddressRequestDTO {

    @ApiModelProperty(value = "zipcode", name = "zipcode", dataType = "string", example = "07854140", position = 1)
    @NotEmpty(message = "{required.zipcode}")
    private String zipcode;

    @ApiModelProperty(value = "street", name = "street", dataType = "string", example = "rua teste", position = 2)
    @NotEmpty(message = "{required.street}")
    private String street;

    @ApiModelProperty(value = "number", name = "number", dataType = "string", example = "123", position = 3)
    @NotEmpty(message = "{required.number}")
    private String number;

    @ApiModelProperty(value = "district", name = "district", dataType = "string", example = "Bairro teste", position = 4)
    @NotEmpty(message = "{required.district}")
    private String district;

    @ApiModelProperty(value = "city", name = "city", dataType = "string", example = "cidade teste", position = 5)
    @NotEmpty(message = "{required.city}")
    private String city;

    @ApiModelProperty(value = "ufId", name = "ufId", dataType = "Long", example = "2", position = 6)
    @NotNull(message = "{required.ufId}")
    private Long ufId;

}
