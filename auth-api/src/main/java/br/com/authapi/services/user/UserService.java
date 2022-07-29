package br.com.authapi.services.user;

import br.com.authapi.entities.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> findAllUsers(Pageable pageable);

    User findUserById(Long id);

    User findUserByEmail(String email);

    User saveUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(User user);
}
