package com.luoshengsha.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	/** 日志记录 **/
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/login")
	public String login(HttpSession session, String name, String password) {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(name.trim(), password.trim());
			// 获取当前的Subject
			Subject currentUser = SecurityUtils.getSubject();
			
			currentUser.login(token);
			logger.info("用户校验成功！");
			
			System.out.println("admin: " + currentUser.hasRole("admin"));
			
			//获取用户信息
			String username = (String)currentUser.getPrincipal();
			session.setAttribute("username", username);
			
			return username;
		} catch (AuthenticationException e) {
			logger.error("用户校验失败", e);
			return "用户校验失败";
		}
	}
}
