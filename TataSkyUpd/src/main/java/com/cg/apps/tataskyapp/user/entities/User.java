package com.cg.apps.tataskyapp.user.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cg.apps.tataskyapp.account.entities.Account;

@Entity
@Table(name = "user_1")
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Size(min = 5, max = 20)
	private String username;

	@Column(name = "first_name")
	@NotBlank
	@Size(min = 2, max = 15)
	@Pattern(regexp = "[a-zA-Z]*")
	private String firstName;
	@NotBlank
	@Size(min = 2, max = 10)
	@Column(name = "last_name")
	@Pattern(regexp = "[a-zA-Z]*")
	private String lastName;

	@NotBlank
	@Size(min = 2, max = 20)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
	private String password;

	@NotBlank
	@Size(min = 2, max = 15)
	private String role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "acc_id")
	private Account account;

	public Long getId() {
		return id;
	}

	public User() {

	}

	public User(String username, String firstName, String lastName, String password, String role) {

		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User(User user) {
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.account = user.getAccount();
	}

	public void copy(User user) {
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
		this.role = user.getRole();
//		this.account = user.getAccount();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User(Long id, String username, String firstName, String lastName, String password, String role,
			Account account) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
		this.account = account;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", role=" + role + ", account=" + account.getAccountId() + "]";
	}

}