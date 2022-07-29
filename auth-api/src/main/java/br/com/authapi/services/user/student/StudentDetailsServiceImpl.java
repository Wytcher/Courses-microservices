package br.com.authapi.services.user.student;

import br.com.authapi.entities.user.User;
import br.com.authapi.entities.user.student.StudentDetails;
import br.com.authapi.exceptions.business.ObjectNotFoundException;
import br.com.authapi.repository.user.student.StudentDetailsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {

    private final StudentDetailsRepository studentDetailsRepository;

    public StudentDetailsServiceImpl(StudentDetailsRepository studentDetailsRepository) {
        this.studentDetailsRepository = studentDetailsRepository;
    }

    @Override
    public Page<StudentDetails> findAllStudents(Pageable pageable) {
        return  studentDetailsRepository.findAll(pageable);
    }

    @Override
    public StudentDetails findStudentDetailsById(Long id) {
        return studentDetailsRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Student for the given id %s was not found", id)));
    }

    @Override
    public StudentDetails findStudentDetailsByUserId(Long userId) {
        return studentDetailsRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Student for the user id %s was not found", userId)));
    }

    @Override
    public StudentDetails saveStudentDetails(StudentDetails studentDetails) {
        return studentDetailsRepository.save(studentDetails);
    }

    @Override
    public StudentDetails updateStudentDetails(Long id, StudentDetails studentDetails) {
        findStudentDetailsById(id);
        return studentDetailsRepository.save(studentDetails);
    }

    public StudentDetails createStudentDetails(StudentDetails studentDetailsEntity, User createdUser) {
        studentDetailsEntity.setUser(createdUser);

        return saveStudentDetails(studentDetailsEntity);
    }
}
