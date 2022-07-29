package br.com.authapi.services.user;

import br.com.authapi.entities.user.User;
import br.com.authapi.exceptions.business.ObjectNotFoundException;
import br.com.authapi.repository.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User for the given id %s was not found", id)));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository
                .findUserByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User for the given email %s was not found", email)));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        findUserById(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {

    }

    public User createUser(User userEntity) {
        var bcryptPassword = new BCryptPasswordEncoder();

        userEntity.setPassword(bcryptPassword.encode(userEntity.getPassword()));

        return saveUser(userEntity);
    }
}
