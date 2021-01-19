package com.blue.matrixdemo.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.blue.matrixdemo.model.UserRole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expirationTime;
	
	private Key key;
	
	@PostConstruct
	public void init(){
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public Claims getAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}
	
	public String getUsernameFromToken(String token) {
		return getAllClaims(token).getSubject();
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getAllClaims(token).getExpiration();
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(UserDetails user) {
		Map<String, String> claims = new HashMap<>();
		String role = user.getAuthorities()
				.contains(
					new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.name())
				)?UserRole.ROLE_ADMIN.name():UserRole.ROLE_USER.name();
		claims.put("role", role);
		return doGenerateToken(claims, user.getUsername());
	}
	
	private String doGenerateToken(Map<String, String> claims, String username) {		
		final Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expirationDate)
				.signWith(key)
				.compact();
	}
	
	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}
}
