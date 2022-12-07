package com.sunbeam.blogsapp.entitiesanddaos;

import java.util.Date;

public class User {

	private int id;
	private String fname;
	private String email;
	private String password;
	private String phone;
	private Date cdate;

	public User() {
		// def
	}

	public User(String fname, String email, String password, String phone, Date cdate) {
		super();
		this.fname = fname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.cdate = cdate;
	}

	public User(int id, String fname, String email, String password, String phone, Date cdate) {
		super();
		this.id = id;
		this.fname = fname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.cdate = cdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", email=" + email + ", password=" + password + ", phone="
				+ phone + ", cdate=" + cdate + "]";
	}

}
