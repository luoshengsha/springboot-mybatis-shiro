package com.luoshengsha.mapper;

import java.util.List;

import com.luoshengsha.bean.Permission;

public interface PermissionMapper {

	public void save(Permission permission);
	
	public void update(Permission permission);
	
	public void delete(int id);
	
	public Permission find(int id);
	
	public List<Permission> query();
}
