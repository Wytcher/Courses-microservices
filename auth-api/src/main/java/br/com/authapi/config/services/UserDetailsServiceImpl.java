package br.com.authapi.config.services;

import br.com.authapi.entities.user.User;
import br.com.authapi.entities.user.UserMapper;
import br.com.authapi.exceptions.business.ObjectNotFoundException;
import br.com.authapi.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User findUserByEmail(String email) {
        return userRepository
                .findUserByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User for the given email %s was not found", email)));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserMapper.userToPrincipal(findUserByEmail(email));
    }
}
