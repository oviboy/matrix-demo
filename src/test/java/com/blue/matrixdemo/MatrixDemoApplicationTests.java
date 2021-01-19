package com.blue.matrixdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blue.matrixdemo.model.UserEntity;
import com.blue.matrixdemo.model.UserRole;
import com.blue.matrixdemo.repo.UserRepository;

@SpringBootTest
class MatrixDemoApplicationTests {
	@Autowired
	private UserRepository ur;
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PasswordEncoder pe;

	@Test
	void contextLoads() {
		/*UserEntity u = new UserEntity();
		u.setFirst_name("Ovidiu");
		u.setLast_name("Negrut");
		u.setUsername("ovidiu.negrut@gmail.com");
		u.setPassword(pe.encode("mypass"));
		u.setRole(UserRole.ROLE_ADMIN.name());
		ur.save(u);*/
	}

}
