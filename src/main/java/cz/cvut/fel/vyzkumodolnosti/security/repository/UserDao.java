package cz.cvut.fel.vyzkumodolnosti.security.repository;

import cz.cvut.fel.vyzkumodolnosti.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

	@Modifying
	@Query(value = "update User u set u.password = :newPassword where u.id = :id")
	void changeUserPassword(Integer id, String newPassword);
}
