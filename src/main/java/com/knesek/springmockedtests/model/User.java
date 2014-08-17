package com.knesek.springmockedtests.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author knesek
 * Created on: 17/08/14
 */
@Entity
public class User {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	Long id;

	private String userName;

	private String password;

	private String email;

	public User() {
	}

	public User(String email, String userName, String password) {
		this.email = email;
		this.password = password;
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", email='" + email + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
