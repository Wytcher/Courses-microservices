package br.com.authapi.services.user.employee;

import br.com.authapi.entities.user.employee.EmployeeDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeDetailsService {

    Page<EmployeeDetails> findAllEmployees(Pageable pageable);

    EmployeeDetails findEmployeeDetailsById(Long id);

    EmployeeDetails findEmployeeDetailsByUserId(Long userId);

    EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails);

    EmployeeDetails updateEmployeeDetails(Long id, EmployeeDetails employeeDetails);
}
