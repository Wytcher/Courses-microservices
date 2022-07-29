package br.com.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegisteredDTO {

    private String email;
    private String subject;
    private String message;
    private String name;
}
