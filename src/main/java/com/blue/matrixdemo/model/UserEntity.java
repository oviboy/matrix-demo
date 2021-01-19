package com.blue.matrixdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "first_name", nullable = false)
	private String first_name;
	@Column(name = "last_name", nullable = false)
	private String last_name;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 10)
	private UserRole role;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = true)	//user can be in "No Group"
    private GroupEntity group;
	
	public GroupEntity getGroup() {
        return this.group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
	
	public UserEntity() {
		
	}
	
	public UserEntity(String fn, String ln, String email, String pass, UserRole roleName) {
		this.setFirst_name(fn);
		this.setLast_name(ln);
		this.setUsername(email);
		this.setPassword(pass);
		this.setRole(roleName);
	}
	
	public String getRole() {
		return this.role.name();
	}

	public void setRole(UserRole roleName) {
		this.role = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String email) {
		this.username = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserEntity))
            return false;
        UserEntity other = (UserEntity) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
