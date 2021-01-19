package com.blue.matrixdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.matrixdemo.model.GroupEntity;
import com.blue.matrixdemo.model.GroupEntityDTO;
import com.blue.matrixdemo.repo.GroupRepository;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupRepository gr;
	
	@Override
	public GroupEntity save(GroupEntityDTO groupData) {
		GroupEntity u = groupData.getGroupFromDTO();
		return gr.save(u);
	}
	
	@Override
	public GroupEntity save(GroupEntity group) {
		return gr.save(group);
	}

	@Override
	public List<GroupEntity> getAllGroups() {
		List<GroupEntity> l = new ArrayList<>();
		gr.findAll().iterator().forEachRemaining(l::add);
		return l;
	}

	@Override
	public GroupEntity getGroup(String name) {
		return gr.findByGroupName(name);
	}

	@Override
	public Optional<GroupEntity> getById(Long id) {
		return gr.findById(id);
	}

	@Override
	public void delete(Long id) {
		gr.deleteById(id);
	}

}
