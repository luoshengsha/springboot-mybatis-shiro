package com.luoshengsha.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luoshengsha.Application;
import com.luoshengsha.bean.Permission;
import com.luoshengsha.bean.Role;
import com.luoshengsha.bean.User;
import com.luoshengsha.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void save() {
		User user = new User();
		user.setName("luoshengsha3");
		user.setPassword("123456");
		userService.save(user);
	}
	
	@Test
	public void find() {
		User user = userService.find(1);
		System.out.println(user.getName() + " " + user.getPassword());
		for(Role role : user.getRoles()) {
			System.out.println(role.getName());
			for(Permission p : role.getPermissions()) {
				System.out.println(p.getPermission() + " " + p.getDescription());
			}
		}
	}
	
	@Test
	public void query() {
		PageHelper.startPage(1, 1);
		List<User> users = userService.query();
		PageInfo<User> page = new PageInfo<User>(users);
		for(User user : users) {
			System.out.println(user.getName());
		}
		
		System.out.println("每页数量: " + page.getPageSize() + " 总记录数: " + page.getTotal() + " 当前页码: " + page.getPageNum() + " 下一页码: " + page.getNextPage() + " 总页码：" + page.getPages());
	}
	
	@Test
	public void getRoles() {
		User user = userService.find(1);
		List<Role> roles = userService.getRoles(user);
		for(Role role : roles) {
			System.out.println(role.getName());
			for(Permission p : role.getPermissions()) {
				System.out.println(p.getPermission() + " " + p.getDescription());
			}
		}
	}
}
