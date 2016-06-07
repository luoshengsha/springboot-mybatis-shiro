package com.luoshengsha.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.luoshengsha.bean.Permission;
import com.luoshengsha.bean.Role;
import com.luoshengsha.bean.User;
import com.luoshengsha.service.UserService;

/**
 * 自定义shiro realm
 * @author luoshengsha
 *
 * 2016年5月10日-下午6:40:21
 */
public class ShiroRealm extends AuthorizingRealm {
	/** 日志记录 **/
	protected final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
	
	@Resource
	private UserService userService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		String username = (String)principals.getPrimaryPrincipal();
		List<Role> roles = userService.getByName(username).getRoles();
		if(roles != null) {
			Set<String> roleSet = new HashSet<String>();
			Set<String> permissionSet = new HashSet<String>();
			
			for(Role role : roles) {
				roleSet.add(role.getName());
				for(Permission p : role.getPermissions()) {
					permissionSet.add(p.getPermission());
				}
			}
			
			authorizationInfo.setRoles(roleSet);
			authorizationInfo.setStringPermissions(permissionSet); 
		}
        
        return authorizationInfo; 
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = (String)token.getPrincipal(); 
		
		User user = userService.getByName(username);
		
		if(user == null) { 
            throw new UnknownAccountException();//没找到帐号 
        }
			
		return new SimpleAuthenticationInfo(user.getName(), user.getPassword(),getName());
	}
}
