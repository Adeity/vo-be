package cz.cvut.fel.pc2e.garminworker.security.service;

import cz.cvut.fel.pc2e.garminworker.security.repository.UserDao;
import cz.cvut.fel.pc2e.garminworker.security.User;
import cz.cvut.fel.pc2e.garminworker.security.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final UserPasswordChangerService userPasswordChangerService;
    private final UserDao userDao;

    @Autowired
    public UserDetailsService(UserPasswordChangerService userPasswordChangerService, UserDao userDao) {
		this.userPasswordChangerService = userPasswordChangerService;
		this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }
        return new UserDetails(user.get());
    }

	public void changePassword(User user, String oldPassword, String newPassword, String newPasswordRepeated) {
		userPasswordChangerService.changePassword(user, oldPassword, newPassword, newPasswordRepeated);
	}
}