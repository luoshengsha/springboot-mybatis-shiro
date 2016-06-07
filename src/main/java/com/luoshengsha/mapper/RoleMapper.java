package com.luoshengsha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luoshengsha.bean.Permission;
import com.luoshengsha.bean.Role;

public interface RoleMapper {

	public void save(Role role);
	
	public void update(Role role);
	
	public void delete(int id);
	
	public Role find(int id);
	
	public List<Role> query();
	
	public void savePermission(@Param(value="role") Role role, @Param(value="permission") Permission permission);
	
	public void deletePermission(@Param(value="role") Role role);
}
