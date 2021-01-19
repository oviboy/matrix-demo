package com.blue.matrixdemo.model;

public class LoginRequestDTO {
	//Helper class for sending auth request
	String username;
	String password;
	
	public LoginRequestDTO() {
		
	}
	
	public LoginRequestDTO(String user, String pass) {
		this.username = user;
		this.password = pass;
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
}
