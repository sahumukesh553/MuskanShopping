package com.muskan.shop.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class User {
	private int userId;
	private String userName;
	private String userEmail; 
	private String userPassword;
	private String userPhone;
	private String userPic;
	private InputStream userPhoto;
	private String userAddress; 
	private String userType;
	
	@Override
	public String toString() {
		return "{userId : "+userId + ", userName : " + userName + ", userEmail : " + userEmail + ", userPassword : "
				+ userPassword + ", userPhone :" + userPhone + ", userAddress : " + userAddress
				+ ", userType : " + userType + "}";
	}
	
	public InputStream getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(InputStream userPhoto) {
		this.userPhoto = userPhoto;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPic() {
		
		return userPic;
		
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
