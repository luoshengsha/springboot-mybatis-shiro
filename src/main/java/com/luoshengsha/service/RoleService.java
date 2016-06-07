package com.luoshengsha.service;

import java.util.List;

import com.luoshengsha.bean.Role;

public interface RoleService {

	public void save(Role role);

	public void update(Role role);

	public void delete(int id);

	public Role find(int id);

	public List<Role> query();
}
