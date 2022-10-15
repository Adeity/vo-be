package cz.cvut.fel.vyzkumodolnosti.security;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ghdata_user")
public class User extends AbstractEntity {

    @NotNull
    @Column(
            name = "first_name",
            nullable = false,
            unique = true
    )
    private String firstName;

    @NotNull
    @Column(
            name = "last_name",
            nullable = false,
            unique = true
    )
    private String lastName;

    @NotNull
    @Column(
            name = "username",
            nullable = false,
            unique = true
    )
    private String username;

    @NotNull
    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(
            name = "role",
            nullable = false
    )
    private RoleEnum roleEnum;

    public User() {
        this.roleEnum = RoleEnum.GUEST;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void encodePassword(PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }

    public void erasePassword() {
        this.password = null;
    }

    public RoleEnum getRole() {
        return roleEnum;
    }

    public void setRole(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public boolean isAdmin() {
        return roleEnum == RoleEnum.ADMIN;
    }

	public boolean isReader() {
		return roleEnum == RoleEnum.READER;
	}

    @Override
    public String toString() {
        return "User{" +
                firstName + " " + lastName +
                "(" + username + ")}";
    }
}
