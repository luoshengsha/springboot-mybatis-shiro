package com.luoshengsha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.luoshengsha.bean.Role;
import com.luoshengsha.bean.User;
import com.luoshengsha.mapper.UserMapper;

/**
 * 用户接口实现
 * @author luoshengsha
 */
@Service
@Cacheable(value="my-ehcache")
public class UserServiceImpl implements UserService {
    
	@Autowired
	private UserMapper mapper;
	
	@Override
	public void save(User user) {
		mapper.save(user);
	}

	@CacheEvict(value="my-ehcache",key="#user.getName()")
	@Override
	public void update(User user) {
		mapper.update(user);
	}

	@Cacheable(value="my-ehcache")
	@Override
	public User find(int id) {
		return mapper.find(id);
	}

	@Override
	public User getByName(String name) {
		return mapper.getByName(name);
	}

	@Cacheable(value="my-ehcache")
	@Override
	public List<User> query() {
		return mapper.query();
	}

	@Override
	public void authorize(User user, List<Role> roles) {
		for(Role role : roles) {
			mapper.authorize(user, role);
		}
	}

	@Override
	public List<Role> getRoles(User user) {
		return mapper.getRoles(user);
	}

}
