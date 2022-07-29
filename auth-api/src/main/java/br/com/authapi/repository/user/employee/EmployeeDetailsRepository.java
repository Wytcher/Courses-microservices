package br.com.authapi.repository.user.employee;

import br.com.authapi.entities.user.employee.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {

    Optional<EmployeeDetails> findEmployeeDetailsByUserId(Long userId);
}
