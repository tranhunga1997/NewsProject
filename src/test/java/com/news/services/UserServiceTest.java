package com.news.services;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.news.constants.Constant;
import com.news.models.User;

@SpringBootTest
class UserServiceTest {
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	
	@Test
	void testAuthenticate_正常() {
		User user = new User("admin","admin123");
		session.setAttribute(Constant.USER_SESSION, user);
		boolean checkLogin = userService.authenticate(session);
		assertEquals(true, checkLogin);
	}
	
	@Test
	void testAuthenticate_異常() {
		User user = new User("admin","aaaaaaaaaaaaaaaaa");
		session.setAttribute(Constant.USER_SESSION, user);
		boolean checkLogin = userService.authenticate(session);
		assertEquals(false, checkLogin);
	}

}
