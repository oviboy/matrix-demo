package com.blue.matrixdemo.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blue.matrixdemo.model.UserEntity;
import com.blue.matrixdemo.model.UserEntityDTO;
import com.blue.matrixdemo.model.UserRole;
import com.blue.matrixdemo.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl us;
	
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody(required = true) UserEntityDTO userData) {
		try {
			UserEntity user = us.save(userData);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
			return ResponseEntity.created(location).body(user);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable (value = "id") Long id) {
		try {
			Optional<UserEntity> u = us.getById(id);
			if(!u.isPresent()) {
				return ResponseEntity.unprocessableEntity().build();
			}
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
					.getAuthentication()
                    .getPrincipal();
			//delete the user as long as it not logged user or admin
			if(u.get().getRole() != UserRole.ROLE_ADMIN.name() &&
					userDetails.getUsername() != u.get().getUsername()) {
				us.delete(id);
			}
			else {
				return ResponseEntity.badRequest().body("you can't detele yourself or ADMIN user!");
			}
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}
}
