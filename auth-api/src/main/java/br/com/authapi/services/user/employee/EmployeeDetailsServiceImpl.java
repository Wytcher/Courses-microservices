package br.com.authapi.services.user.employee;

import br.com.authapi.entities.user.User;
import br.com.authapi.entities.user.employee.EmployeeDetails;
import br.com.authapi.exceptions.business.ObjectNotFoundException;
import br.com.authapi.repository.user.employee.EmployeeDetailsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private final EmployeeDetailsRepository employeeDetailsRepository;

    public EmployeeDetailsServiceImpl(EmployeeDetailsRepository employeeDetailsRepository) {
        this.employeeDetailsRepository = employeeDetailsRepository;
    }

    @Override
    public Page<EmployeeDetails> findAllEmployees(Pageable pageable) {
        return employeeDetailsRepository.findAll(pageable);
    }

    @Override
    public EmployeeDetails findEmployeeDetailsById(Long id) {
        return employeeDetailsRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Employee for the given id %s was not found", id)));
    }

    @Override
    public EmployeeDetails findEmployeeDetailsByUserId(Long userId) {
        return employeeDetailsRepository
                .findEmployeeDetailsByUserId(userId)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Employee for the given user id %s was not found", userId)));
    }

    @Override
    public EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails) {
        return employeeDetailsRepository.save(employeeDetails);
    }

    @Override
    public EmployeeDetails updateEmployeeDetails(Long id, EmployeeDetails employeeDetails) {
        findEmployeeDetailsById(id);
        return employeeDetailsRepository.save(employeeDetails);
    }

    public EmployeeDetails createEmployeeDetails(EmployeeDetails employeeDetailsEntity, User createdEmployee) {
        employeeDetailsEntity.setUser(createdEmployee);

        return saveEmployeeDetails(employeeDetailsEntity);
    }
}
