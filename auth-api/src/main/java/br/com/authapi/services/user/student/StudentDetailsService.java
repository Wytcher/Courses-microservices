package br.com.authapi.services.user.student;

import br.com.authapi.entities.user.student.StudentDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentDetailsService {

    Page<StudentDetails> findAllStudents(Pageable pageable);

    StudentDetails findStudentDetailsById(Long id);

    StudentDetails findStudentDetailsByUserId(Long userId);

    StudentDetails saveStudentDetails(StudentDetails studentDetails);

    StudentDetails updateStudentDetails(Long id, StudentDetails studentDetails);
}
