package gr.aueb.cf.teacherapp.service;


import gr.aueb.cf.teacherapp.model.User;
import gr.aueb.cf.teacherapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
