package com.cafe24.lms.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_no")
	private Long no;

	@NotEmpty
	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Email
	@NotEmpty
	@Column(name = "email", nullable = false, length = 60)
	private String email;

	@NotEmpty
	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "reg_date")
	private Date regDate;

	@OneToMany(mappedBy = "user" ,fetch= FetchType.LAZY)
	private List<Rent> rents;

	public User() {
		rents = new ArrayList<Rent>();
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	@Override
	public String toString() {
		return "User [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", role=" + role + ", regDate=" + regDate +  "]";
	}
}
