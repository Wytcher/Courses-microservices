package br.com.authapi.repository.user.student;

import br.com.authapi.entities.user.student.StudentAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentAddressRepository extends JpaRepository<StudentAddress, Long> {

    Optional<StudentAddress> findByUserId(Long userId);
}
