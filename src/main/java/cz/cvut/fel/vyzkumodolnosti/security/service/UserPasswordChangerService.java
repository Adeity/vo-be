package cz.cvut.fel.vyzkumodolnosti.security.service;

import cz.cvut.fel.vyzkumodolnosti.security.repository.UserDao;
import cz.cvut.fel.vyzkumodolnosti.security.User;
import cz.cvut.fel.vyzkumodolnosti.security.exception.CantUpdatePasswordException;
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
	public void changePassword(User user, String oldPassword, String newPassword, String newPasswordRepeated) {
		if (!newPassword.equals(newPasswordRepeated)) {
			throw new CantUpdatePasswordException("Nova hesla se neshoduji");
		}
		if (oldPassword == null) {
			throw new CantUpdatePasswordException("Soucasne heslo neni vyplneno");
		}
		if (newPassword == null) {
			throw new CantUpdatePasswordException("Nove heslo neni vyplneno");
		}
		boolean passwordMatches = passwordEncoder.matches(oldPassword, user.getPassword());
		if (!passwordMatches) {
			throw new CantUpdatePasswordException("Vase soucasne heslo se neshoduje se zadanym heslem");
		}
		validatePassword(newPassword);
		try {
			String encryptedPassword = passwordEncoder.encode(newPassword);
			dao.changeUserPassword(user.getId(), encryptedPassword);
		} catch (Exception e) {
			throw new CantUpdatePasswordException("Nastala chyba pri enkodovani hesla");
		}
	}

	private void validatePassword (String password) {
		if (password.length() < 1) {
			throw new CantUpdatePasswordException("Nove heslo je prilis kratke");
		}
	}
}