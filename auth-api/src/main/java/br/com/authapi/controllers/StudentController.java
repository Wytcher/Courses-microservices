package br.com.authapi.controllers;

import br.com.authapi.dto.rabbitmq.StudentRegisteredDTO;
import br.com.authapi.dto.student.StudentCreateRequestDTO;
import br.com.authapi.dto.student.StudentResponseDTO;
import br.com.authapi.entities.role.RoleName;
import br.com.authapi.entities.user.User;
import br.com.authapi.entities.user.student.StudentAddress;
import br.com.authapi.entities.user.student.StudentDetails;
import br.com.authapi.rabbitmq.StudentRabbitMQProducer;
import br.com.authapi.services.role.RoleServiceImpl;
import br.com.authapi.services.user.UserServiceImpl;
import br.com.authapi.services.user.student.StudentAddressServiceImpl;
import br.com.authapi.services.user.student.StudentDetailsServiceImpl;
import br.com.authapi.utils.MapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("api/v1/auth/students")
public class StudentController {

    private final MapperUtils mapperUtils = new MapperUtils();
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
    private final StudentDetailsServiceImpl studentDetailsService;
    private final StudentAddressServiceImpl studentAddressService;
    private final StudentRabbitMQProducer studentRabbitMQProducer;

    public StudentController(UserServiceImpl userService, RoleServiceImpl roleService, StudentDetailsServiceImpl studentDetailsService, StudentAddressServiceImpl studentAddressService, StudentRabbitMQProducer studentRabbitMQProducer) {
        this.userService = userService;
        this.roleService = roleService;
        this.studentDetailsService = studentDetailsService;
        this.studentAddressService = studentAddressService;
        this.studentRabbitMQProducer = studentRabbitMQProducer;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<StudentResponseDTO>> findAllStudents(Pageable pageable) {
        var pageableStudents = studentDetailsService.findAllStudents(pageable);

        var pageableStudentsResponseDTO = mapperUtils.mapEntityPageIntoDtoPage(pageableStudents, StudentResponseDTO.class);

        return ResponseEntity.ok(pageableStudentsResponseDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponseDTO> findStudent(@PathVariable Long id) {
        var student = studentDetailsService.findStudentDetailsById(id);

        var studentResponseDTO = mapperUtils.map(student, StudentResponseDTO.class);

        return ResponseEntity.ok(studentResponseDTO);
    }

    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponseDTO> createStudent(@Validated @RequestBody StudentCreateRequestDTO studentCreateRequestDTO) {
        var studentRoles = roleService.findRoleByName(RoleName.STUDENT);

        var userEntity = mapperUtils.map(studentCreateRequestDTO.getUser(), User.class);
        userEntity.setRoles(studentRoles);
        var createdUser = userService.createUser(userEntity);

        var studentDetailsEntity = mapperUtils.map(studentCreateRequestDTO, StudentDetails.class);
        var createdStudentDetails = studentDetailsService.createStudentDetails(studentDetailsEntity, createdUser);

        var studentAddressEntity = mapperUtils.map(studentCreateRequestDTO.getStudentAddress(), StudentAddress.class);
        studentAddressService.createStudentAddress(studentAddressEntity, studentCreateRequestDTO.getStudentAddress().getUfId(), createdUser);

        var studentResponseDTO = mapperUtils.map(createdStudentDetails, StudentResponseDTO.class);

        sendRabbitMQRegisteredStudent(createdUser, studentResponseDTO.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponseDTO);
    }

    private void sendRabbitMQRegisteredStudent(User createdUser, String fullName) {
        var studentRegisteredDTO = new StudentRegisteredDTO();
        studentRegisteredDTO.setEmail(createdUser.getEmail());
        studentRegisteredDTO.setSubject("Conta registrada!");
        studentRegisteredDTO.setMessage(
                "Olá " + fullName + " você acabou de ser registrado no portal de cursos da " +
                        "" + "universidade seu email de acesso é: " + "" + createdUser.getEmail() + " e sua senha temporária é: 12345678"
        );
        studentRabbitMQProducer.sendAccountRegisteredMessage(studentRegisteredDTO);
    }
}
