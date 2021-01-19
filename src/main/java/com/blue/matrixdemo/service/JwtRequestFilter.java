package com.blue.matrixdemo.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/*
 * Will process any authorization request based on JWT token
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private UserServiceImpl us;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		String user = null;
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			String jwt = authHeader.substring(7);	//"Bearer " 0-7
			user = jwtUtil.getUsernameFromToken(jwt);
		}
		
		if(user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = us.loadUserByUsername(user); 
			UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			usernamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
		}
		
		filterChain.doFilter(request, response);//process any available filters, next
	}

}
