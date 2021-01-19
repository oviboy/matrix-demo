package com.blue.matrixdemo.model;

public class UserEntityDTO { 
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    
    public UserEntityDTO() {
    	
    }
    
    public UserEntityDTO(String fn, String ln, String email, String pass) {
    	this.email = email;
    	this.first_name = fn;
    	this.last_name = ln;
    	this.password = pass;
    }

    public UserEntity getUserFromDTO(){
    	UserEntity user = null;
    	if(this.first_name != null 
    		&& this.last_name != null 
    		&& password != null
    		&& this.email != null) {
    		
    		user = new UserEntity();
    		user.setFirst_name(this.first_name);
    		user.setLast_name(this.last_name);
    		user.setPassword(this.password);
    		user.setUsername(this.email);
        
    		user.setRole(UserRole.ROLE_USER);
    	}
        
        return user;
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

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	} 
}