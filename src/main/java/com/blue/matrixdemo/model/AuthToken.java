package com.blue.matrixdemo.model;

public class AuthToken {
	private final String token;

	public AuthToken(String token) {
		this.token = token;
	}
	
	public String getAuthToken() {
		return this.token;
	}
}
