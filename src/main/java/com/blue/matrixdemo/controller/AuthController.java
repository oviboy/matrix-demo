package com.blue.matrixdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blue.matrixdemo.model.AuthToken;
import com.blue.matrixdemo.model.LoginRequestDTO;
import com.blue.matrixdemo.service.JwtUtil;
import com.blue.matrixdemo.service.UserServiceImpl;

@Controller
@RequestMapping(path = {"/authenticate", "/login", "/auth"})
public class AuthController {
	@Autowired
	private UserServiceImpl us;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping()
	public ResponseEntity<?> returnAuthToken(@RequestBody LoginRequestDTO req) {
		try {
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		}
		catch(AuthenticationException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		UserDetails user = null;
		try {
			user = us.loadUserByUsername(req.getUsername());
		}
		catch(UsernameNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		String jwt = jwtUtil.generateToken(user);
		
		return ResponseEntity.ok(new AuthToken(jwt).getAuthToken());	//in case of successful auth
	}
}
