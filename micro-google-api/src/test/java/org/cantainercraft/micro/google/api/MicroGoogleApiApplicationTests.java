package org.cantainercraft.micro.google.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@SpringBootTest
class MicroGoogleApiApplicationTests {

	@Test
	void encodeBCrypt() {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String name = "artem";
		String result =bcrypt.encode(name);
		System.out.println(result);
		Assertions.assertTrue(bcrypt.matches(name,result));
	}
//	@Test
//	void encodeArgon() {
//		Argon2PasswordEncoder bcrypt = new Argon2PasswordEncoder();
//		String name = "artem";
//		String result =bcrypt.encode(name);
//		System.out.println(result);
//		Assertions.assertTrue(bcrypt.matches(name,result));
//	}
//@Test
//void encodeBCrypt() {
//	Pbkdf2PasswordEncoder bcrypt = new Pbkdf2PasswordEncoder();
//	String name = "artem";
//	String result =bcrypt.encode(name);
//	System.out.println(result);
//	Assertions.assertTrue(bcrypt.matches(name,result));
//}



}
