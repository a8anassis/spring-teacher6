package gr.aueb.cf.teacherapp.repository;

import gr.aueb.cf.teacherapp.core.enums.Role;
import gr.aueb.cf.teacherapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByRole(Role role);
}
