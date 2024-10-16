package gr.aueb.cf.teacherapp.service;

import gr.aueb.cf.teacherapp.model.User;
import gr.aueb.cf.teacherapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackOn = Exception.class)
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encrypt password
        return userRepository.save(user);
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
