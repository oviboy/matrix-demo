package com.blue.matrixdemo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blue.matrixdemo.model.GroupEntity;
import com.blue.matrixdemo.model.GroupEntityDTO;
import com.blue.matrixdemo.model.UserEntity;
import com.blue.matrixdemo.service.GroupServiceImpl;
import com.blue.matrixdemo.service.UserServiceImpl;

@RestController
@RequestMapping("/group")
public class GroupController {	
	@Autowired
	private GroupServiceImpl gs;
	
	@Autowired
	private UserServiceImpl us;
	
	@GetMapping()
	public List<GroupEntity> getGroups() {
		return gs.getAllGroups();
	}
	
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody(required = true) GroupEntityDTO groupData) {
		try {
			GroupEntity group = gs.save(groupData);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(group.getId()).toUri();
			return ResponseEntity.created(location).body(group);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{groupId}/{userId}")
	public ResponseEntity<?> update(@PathVariable(value="groupId") Long groupId, @PathVariable(value="userId") Long userId) {
		try {
			Optional<GroupEntity> g = gs.getById(groupId);
			if(g.isPresent()) {
				Optional<UserEntity> u = us.getById(userId);
				if(u.isPresent()) {
					u.get().setGroup(g.get());
					us.save(u.get());
				}
				else {
					return ResponseEntity.badRequest().body("User not found!");
				}
			}
			else {
				return ResponseEntity.badRequest().body("Group not found!");
			}
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable (value = "id") Long id) {
		try {
			Optional<GroupEntity> g = gs.getById(id);
			if(g.isPresent())
				gs.delete(id);
			else
				return ResponseEntity.unprocessableEntity().build();
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}
}
