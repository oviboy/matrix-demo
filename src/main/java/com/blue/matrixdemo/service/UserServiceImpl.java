package com.blue.matrixdemo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blue.matrixdemo.model.UserEntity;
import com.blue.matrixdemo.model.UserEntityDTO;
import com.blue.matrixdemo.model.UserRole;
import com.blue.matrixdemo.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserRepository ur;
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = this.getUser(email);
		if (user == null) throw new UsernameNotFoundException("No user found by email address: " + email);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        String userrole = user.getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(userrole));

		return buildUserForAuthentication(user, grantedAuthorities);
	}
	
	private UserDetails buildUserForAuthentication(UserEntity user, Set<GrantedAuthority> authorities) {
		//or could use an UserAdapter that implements UserDetails class and return that useradapter
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
    }
	
	@Override
	public UserEntity getUser(String email) {
		return ur.findByUsername(email);
	}
	
	@Override
	public Optional<UserEntity> getById(Long id) {
        return ur.findById(id);
    }
	
	@Override
	public List<UserEntity> getAllUsers() {
		List<UserEntity> l = new ArrayList<>();
        ur.findByRoleNotContaining(UserRole.ROLE_ADMIN.name()).iterator().forEachRemaining(l::add);
        return l;
    }

	@Override
	public UserEntity save(UserEntityDTO user) {
		UserEntity u = user.getUserFromDTO();
		u.setPassword(encoder.encode(user.getPassword()));
		return ur.save(u);
	}

	@Override
	public UserEntity getAdmin() {
		return ur.findByRoleEquals(UserRole.ROLE_USER.name());
	}

	@Override
	public void delete(Long id) {
		ur.deleteById(id);
	}

	@Override
	public UserEntity save(UserEntity user) {
		return ur.save(user);
	}
}
