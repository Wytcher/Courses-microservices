package br.com.authapi.services.user.student;

import br.com.authapi.entities.user.User;
import br.com.authapi.entities.user.student.StudentAddress;
import br.com.authapi.exceptions.business.ObjectNotFoundException;
import br.com.authapi.repository.user.student.StudentAddressRepository;
import br.com.authapi.services.brazilUf.BrazilUfServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentAddressServiceImpl implements StudentAddressService {

    private final StudentAddressRepository studentAddressRepository;
    private final BrazilUfServiceImpl brazilUfService;

    public StudentAddressServiceImpl(StudentAddressRepository studentAddressRepository, BrazilUfServiceImpl brazilUfService) {
        this.studentAddressRepository = studentAddressRepository;
        this.brazilUfService = brazilUfService;
    }

    @Override
    public StudentAddress findStudentAddressById(Long id) {
        return studentAddressRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Student address for the given id %s was not found", id)));
    }

    @Override
    public StudentAddress findStudentAddressByUserId(Long userId) {
        return studentAddressRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Student address for the given user id %s was not found", userId)));
    }

    @Override
    public StudentAddress saveStudentAddress(StudentAddress studentAddress) {
        return studentAddressRepository.save(studentAddress);
    }

    @Override
    public StudentAddress updateStudentAddress(Long id, StudentAddress studentAddress) {
        findStudentAddressById(id);
        return studentAddressRepository.save(studentAddress);
    }

    public StudentAddress createStudentAddress(StudentAddress studentAddressEntity, Long brazilUfId, User createdUser) {
        var brazilUf = brazilUfService.findBrazilUfById(brazilUfId);

        studentAddressEntity.setUfId(brazilUf);
        studentAddressEntity.setUser(createdUser);

        return saveStudentAddress(studentAddressEntity);
    }
}
