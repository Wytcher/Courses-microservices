package br.com.authapi.services.user.student;

import br.com.authapi.entities.user.student.StudentAddress;

public interface StudentAddressService {

    StudentAddress findStudentAddressById(Long id);

    StudentAddress findStudentAddressByUserId(Long userId);

    StudentAddress saveStudentAddress(StudentAddress studentAddress);

    StudentAddress updateStudentAddress(Long id, StudentAddress studentAddress);
}
