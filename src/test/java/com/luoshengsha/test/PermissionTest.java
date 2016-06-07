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
import com.luoshengsha.service.PermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PermissionTest {
	
	@Autowired
	private PermissionService permissionService;
	
	@Test
	public void save() {
		Permission p = new Permission();
		p.setPermission("user:query");
		p.setDescription("查询用户");
		p.setAvailable(true);
		permissionService.save(p);
	}
	
	@Test
	public void find() {
		Permission p = permissionService.find(1);
		System.out.println(p.getPermission() + " " + p.getDescription());
	}
	
	@Test
	public void query() {
		PageHelper.startPage(1, 2);
		List<Permission> permissions = permissionService.query();
		PageInfo<Permission> page = new PageInfo<Permission>(permissions);
		for(Permission p : permissions) {
			System.out.println(p.getPermission());
		}
		
		System.out.println("每页数量: " + page.getPageSize() + " 总记录数: " + page.getTotal() + " 当前页码: " + page.getPageNum() + " 下一页码: " + page.getNextPage() + " 总页码：" + page.getPages());
	}
}
