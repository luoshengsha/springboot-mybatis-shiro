package com.luoshengsha.service;

import java.util.List;

import com.luoshengsha.bean.Role;
import com.luoshengsha.bean.User;

/**
 * 用户接口
 * @author luoshengsha
 */
public interface UserService {
	
	public void save(User user);
	
	public void update(User user);
	
	public User find(int id);
	
	public User getByName(String name);
	
	public List<User> query();
	
	public void authorize(User user,List<Role> roles);
	
	public List<Role> getRoles(User user);
}
