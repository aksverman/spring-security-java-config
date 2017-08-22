package com.rudra.aks.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_user")
public class UserBO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int USERID;
	
	@Column(name = "USERNAME", length = 30)
	private String username;
	
	@Column(name = "EMAILID", length = 50)
	private String emailid;
	
	@Column(name = "PASSWORD", nullable = false, length = 30)
	private String password;

	@Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
	private Role role;
	
	public int getUserid() {
		return USERID;
	}

	public void setUserid(int userid) {
		this.USERID = userid;
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

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserBO [userid=" + USERID + ", username=" + username + ", password=" + password + ", emailid=" + emailid
				+ ", role=" + role + "]";
	}
	
	
	
}
