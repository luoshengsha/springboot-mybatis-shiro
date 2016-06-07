package com.luoshengsha.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.luoshengsha.bean.Permission;
import com.luoshengsha.bean.Role;
import com.luoshengsha.mapper.RoleMapper;

@Service
@Transactional
@Cacheable(value="my-ehcache")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper mapper;
	
	@Override
	public void save(Role role) {
		mapper.save(role);
		for(Permission permission : role.getPermissions()) {
			mapper.savePermission(role, permission);
		}
	}

	@Override
	public void update(Role role) {
		mapper.update(role);
		mapper.deletePermission(role);
		for(Permission permission : role.getPermissions()) {
			mapper.savePermission(role, permission);
		}
	}

	@Override
	public void delete(int id) {
		mapper.delete(id);
	}

	@Override
	public Role find(int id) {
		return mapper.find(id);
	}

	@Override
	public List<Role> query() {
		return mapper.query();
	}

}
