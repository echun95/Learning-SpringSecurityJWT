package com.study.jwtsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class JwtSecurityApplicationTests {
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	void contextLoads() {
	}

	@Test
	void test(){
		String encode = bCryptPasswordEncoder.encode("1234");
		System.out.println("encode = " + encode);
	}

}
