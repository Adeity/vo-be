package cz.cvut.fel.pc2e.garminworker.security.service;

import cz.cvut.fel.pc2e.garminworker.security.repository.UserDao;
import cz.cvut.fel.pc2e.garminworker.security.User;
import cz.cvut.fel.pc2e.garminworker.security.exception.CantUpdatePasswordException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserPasswordChangerService {
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final UserDao dao;

	public UserPasswordChangerService(UserDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void changePassword(User user, String oldPassword, String newPassword) {
		boolean passwordMatches = passwordEncoder.matches(oldPassword, user.getPassword());
		if (!passwordMatches) {
			throw new CantUpdatePasswordException("Current password does not match old password.");
		}
		validatePassword(newPassword);
		String encryptedPassword = passwordEncoder.encode(newPassword);

		dao.changeUserPassword(user.getId(), encryptedPassword);
	}

	private void validatePassword (String password) {
		if (password.length() < 1) {
			throw new CantUpdatePasswordException("New password is invalid");
		}
	}
}