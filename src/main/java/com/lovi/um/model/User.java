package com.lovi.um.model;

import org.springframework.data.annotation.Id;

public class User{
	
	@Id
	private String userId;
	private String name;
	private String address;
	private String mobileNo;
	
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
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", address="
				+ address + ", mobileNo=" + mobileNo + "]";
	}
	

}
