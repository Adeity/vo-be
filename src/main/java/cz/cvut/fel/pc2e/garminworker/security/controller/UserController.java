package cz.cvut.fel.pc2e.garminworker.security.controller;

import cz.cvut.fel.pc2e.garminworker.security.PasswordChangeDto;
import cz.cvut.fel.pc2e.garminworker.security.User;
import cz.cvut.fel.pc2e.garminworker.security.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/security")
public class UserController {
	private final UserDetailsService userDetailsService;

	@Autowired
	public UserController(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
	@PostMapping(value = "/change-password")
	public void changePassword(@RequestBody @Valid PasswordChangeDto passwordChangeDto) {
		 User user = userDetailsService.loadUserByUsername(
						  SecurityContextHolder.getContext().getAuthentication().getName()
				 )
				 .getUser();
		 this.userDetailsService.changePassword(
				 user,
				 passwordChangeDto.getOldPassword(),
				 passwordChangeDto.getNewPassword(),
				 passwordChangeDto.getNewPasswordRepeated()
		 );
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
	@GetMapping(value = "/check-authorized")
	public String checkAuthorized() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}