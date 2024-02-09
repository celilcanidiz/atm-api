package com.atm.service;


import com.atm.dto.CreateUserRequest;
import com.atm.exception.UserAlreadyExistsException;
import com.atm.model.User;
import com.atm.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            user.get().setAccounts(userRepository.findAllAccountsByUser_Id(user.get().getId()));
            return user.get();
        } else {
            log.info("Username with " + username + " not found");
            throw new UsernameNotFoundException("Username with " + username + " not found");
        }
    }

    public User createUser(CreateUserRequest request) throws UserAlreadyExistsException {
        if (userRepository.existsByUsername(request.username())) {
            log.info("User already exists");
            throw new UserAlreadyExistsException("This User already exists");
        }

        if (userRepository.existsByEmail(request.email())) {
            log.info("Email already exists");
            throw new UserAlreadyExistsException("Email already exists");
        }
            User newUser = User.builder()
                    .name(request.name())
                    .username(request.username())
                    .password(passwordEncoder.encode(request.password()))
                    .email(request.email())
                    .authorities(request.authorities())
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .isEnabled(true)
                    .accountNonLocked(true)
                    .build();
            return userRepository.save(newUser);
    }

        public void deleteUserById(Long id) throws EntityNotFoundException {
            userRepository.deleteById(id);
            log.info("User deleted");
    }
}
