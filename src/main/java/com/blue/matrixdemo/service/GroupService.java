package com.blue.matrixdemo.service;

import java.util.List;
import java.util.Optional;

import com.blue.matrixdemo.model.GroupEntity;
import com.blue.matrixdemo.model.GroupEntityDTO;

public interface GroupService {
	GroupEntity save(GroupEntityDTO groupData);
	GroupEntity save(GroupEntity group);
    List<GroupEntity> getAllGroups();
    GroupEntity getGroup(String name);
    Optional<GroupEntity> getById(Long id);
    
    void delete(Long id);
}
