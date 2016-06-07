package com.luoshengsha.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import com.luoshengsha.service.PermissionService;
import com.luoshengsha.service.RoleService;
import com.luoshengsha.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RoleTest {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	@Resource
	private UserService userService;
	
	@Test
	public void save() {
		List<Permission> permissions = permissionService.query();
		Role role = new Role();
		role.setName("超级管理员");
		role.setPermissions(permissions);
		roleService.save(role);
	}
	
	@Test
	public void find() {
		Role role = roleService.find(4);
		System.out.println(role.getName());
		for(Permission p : role.getPermissions()) {
			System.out.println(p.getPermission() + " " + p.getDescription());
		}
	}
	
	@Test
	public void query() {
		PageHelper.startPage(1, 1);
		List<Role> roles = roleService.query();
		PageInfo<Role> page = new PageInfo<Role>(roles);
		for(Role role : roles) {
			System.out.println(role.getName());
		}
		
		System.out.println("每页数量: " + page.getPageSize() + " 总记录数: " + page.getTotal() + " 当前页码: " + page.getPageNum() + " 下一页码: " + page.getNextPage() + " 总页码：" + page.getPages());
	}
	
	@Test
	public void authorize() {
		List<Role> roles = new ArrayList<Role>();
		
		Role role = roleService.find(4);
		
		roles.add(role);
		
		User user = userService.find(1);
		userService.authorize(user,roles);
	}
}
