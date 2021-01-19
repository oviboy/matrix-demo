package com.blue.matrixdemo.service;

import java.util.List;
import java.util.Optional;

import com.blue.matrixdemo.model.UserEntity;
import com.blue.matrixdemo.model.UserEntityDTO;

public interface UserService {
	UserEntity save(UserEntityDTO user);
	UserEntity save(UserEntity user);

    List<UserEntity> getAllUsers();
    UserEntity getUser(String email);
    UserEntity getAdmin();
    Optional<UserEntity> getById(Long id);
    
    void delete(Long id);
}
