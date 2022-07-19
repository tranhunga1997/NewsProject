package com.news.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.news.constants.Constant;
import com.news.models.User;

@Service
public class UserService {
	@Value("${user.username}")
	private String username;
	@Value("${user.password}")
	private String password;
	
	/**
	 * Kiểm tra đăng nhập
	 * username: admin
	 * password: admin123
	 * @param session
	 * @return <code>true</code> xác thực thành công <br>
	 * 			<code>false</code> xác thực thất bại
	 */
	public boolean authenticate(HttpSession session) {
		User user = (User) session.getAttribute(Constant.USER_SESSION);
		if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}
