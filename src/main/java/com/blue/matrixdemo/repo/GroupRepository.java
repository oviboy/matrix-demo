package com.blue.matrixdemo.repo;


import org.springframework.stereotype.Repository;

import com.blue.matrixdemo.model.GroupEntity;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long>{
	GroupEntity findByGroupName(String email);
	GroupEntity findById(long id);
}
