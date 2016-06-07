package com.luoshengsha.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.luoshengsha.bean.Permission;
import com.luoshengsha.mapper.PermissionMapper;

@Service
@Cacheable(value="my-ehcache")
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionMapper mapper;
	
	@Override
	public void save(Permission permission) {
		mapper.save(permission);
	}

	@Override
	@CacheEvict(value="my-ehcache",key="#permission.getName()")
	public void update(Permission permission) {
		mapper.update(permission);
	}

	@Override
	@CacheEvict(value="my-ehcache",key="#permission.getName()")
	public void delete(int id) {
		mapper.delete(id);
	}

	@Override
	@Cacheable(value="my-ehcache")
	public Permission find(int id) {
		return mapper.find(id);
	}

	@Override
	@Cacheable(value="my-ehcache")
	public List<Permission> query() {
		return mapper.query();
	}

}
