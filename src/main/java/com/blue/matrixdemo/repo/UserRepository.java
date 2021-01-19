package com.blue.matrixdemo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blue.matrixdemo.model.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	Optional<UserEntity> findById(long id);
	UserEntity findByUsername(String email);
	List<UserEntity> findByRoleNotContaining(String role);
	UserEntity findByRoleEquals(String role);
}
