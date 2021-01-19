package com.blue.matrixdemo.model;

public class GroupEntityDTO {
	private String name;
	
	public GroupEntityDTO() {
		
	}
	
	public GroupEntityDTO(String name) {
    	this.name = name;
    }
	
	public GroupEntity getGroupFromDTO(){
		GroupEntity group = null;
		
		if(this.name != null) {
			group = new GroupEntity();
	        group.setGroupName(this.name);
		}
        
        return group;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
