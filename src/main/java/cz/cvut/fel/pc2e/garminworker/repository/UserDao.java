package cz.cvut.fel.pc2e.garminworker.repository;

import cz.cvut.fel.pc2e.garminworker.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
