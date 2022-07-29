package br.com.coursesapi.models.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

}
