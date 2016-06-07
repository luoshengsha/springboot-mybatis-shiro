package com.luoshengsha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luoshengsha.bean.Role;
import com.luoshengsha.bean.User;

public interface UserMapper {

	public void save(User user);
	
	public void update(User user);
	
	public User find(int id);
	
	public List<User> query();
	
	public void authorize(@Param(value="user") User user, @Param(value="role") Role role);
	
	public List<Role> getRoles(User user);

	public User getByName(String name);
}
