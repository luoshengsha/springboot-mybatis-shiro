package com.luoshengsha.service;

import java.util.List;

import com.luoshengsha.bean.Permission;

public interface PermissionService {
	public void save(Permission permission);

	public void update(Permission permission);

	public void delete(int id);

	public Permission find(int id);

	public List<Permission> query();
}
