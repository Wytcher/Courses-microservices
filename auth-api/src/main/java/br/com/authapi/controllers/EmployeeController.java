package br.com.authapi.controllers;

import br.com.authapi.dto.employee.EmployeeCreateRequestDTO;
import br.com.authapi.dto.employee.EmployeeResponseDTO;
import br.com.authapi.dto.student.StudentResponseDTO;
import br.com.authapi.entities.role.RoleName;
import br.com.authapi.entities.user.User;
import br.com.authapi.entities.user.employee.EmployeeDetails;
import br.com.authapi.services.role.RoleServiceImpl;
import br.com.authapi.services.user.UserServiceImpl;
import br.com.authapi.services.user.employee.EmployeeDetailsServiceImpl;
import br.com.authapi.utils.MapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/employees")
public class EmployeeController {

    private final MapperUtils mapperUtils = new MapperUtils();
    private final EmployeeDetailsServiceImpl employeeDetailsService;
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public EmployeeController(EmployeeDetailsServiceImpl employeeDetailsService, UserServiceImpl userService, RoleServiceImpl roleService) {
        this.employeeDetailsService = employeeDetailsService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EmployeeResponseDTO>> findAllEmployees(Pageable pageable) {
        var pageableEmployees = employeeDetailsService.findAllEmployees(pageable);

        var pageableEmployeesResponseDTO = mapperUtils.mapEntityPageIntoDtoPage(pageableEmployees, EmployeeResponseDTO.class);

        return ResponseEntity.ok(pageableEmployeesResponseDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponseDTO> findEmployee(@PathVariable Long id) {
        var employee = employeeDetailsService.findEmployeeDetailsById(id);

        var employeeResponseDTO = mapperUtils.map(employee, EmployeeResponseDTO.class);

        return ResponseEntity.ok(employeeResponseDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Validated @RequestBody EmployeeCreateRequestDTO employeeCreateRequestDTO) {
        var employeeRoles = roleService.findRoleByName(RoleName.EMPLOYEE);

        var employeeEntity = mapperUtils.map(employeeCreateRequestDTO.getUser(), User.class);
        employeeEntity.setRoles(employeeRoles);
        var createdEmployee = userService.createUser(employeeEntity);

        var employeeDetailsEntity = mapperUtils.map(employeeCreateRequestDTO, EmployeeDetails.class);
        var createdEmployeeDetails = employeeDetailsService.createEmployeeDetails(employeeDetailsEntity, createdEmployee);

        var employeeResponseDTO = mapperUtils.map(createdEmployeeDetails, EmployeeResponseDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponseDTO);
    }
}
