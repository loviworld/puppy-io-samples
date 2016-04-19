package com.lovi.um.model;

import org.springframework.data.annotation.Id;

public class User{
	
	@Id
	private String userId;
	private String name;
	private String password;
	private String address;
	private String mobileNo;
	
	public User() {
	}
	
	public User(String userId, String name, String password, String address, String mobileNo) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.address = address;
		this.mobileNo = mobileNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
