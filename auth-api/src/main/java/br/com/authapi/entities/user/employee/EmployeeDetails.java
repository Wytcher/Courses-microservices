package br.com.authapi.entities.user.employee;

import br.com.authapi.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_employee_details")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate employeeBirthDate;
    private String employeeCpf;
    private String employeeRg;
    private String employeePhone;
    private String employeeWorkCardNumber;

    @OneToOne
    private User user;
}
