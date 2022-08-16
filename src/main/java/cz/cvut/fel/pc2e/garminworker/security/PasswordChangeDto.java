package cz.cvut.fel.pc2e.garminworker.security;

import javax.validation.constraints.NotNull;

public class PasswordChangeDto {
	@NotNull
	public String oldPassword;
	@NotNull
	public String newPassword;
	@NotNull
	public String newPasswordRepeated;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordRepeated() {
		return newPasswordRepeated;
	}

	public void setNewPasswordRepeated(String newPasswordRepeated) {
		this.newPasswordRepeated = newPasswordRepeated;
	}
}
