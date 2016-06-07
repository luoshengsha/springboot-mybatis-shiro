package com.luoshengsha.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luoshengsha.bean.User;
import com.luoshengsha.service.UserService;

/**
 * 用户控制器
 * @author luoshengsha
 */
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	//@RequiresRoles("admin")
	@RequiresPermissions("user:query")
	@RequestMapping(value="/query")
	public String query() {
		try {
			PageHelper.startPage(1, 2);
			List<User> users = userService.query();
			PageInfo<User> page = new PageInfo<User>(users);
			return ListToString(page.getList());
		} catch (UnauthorizedException e) {
			System.out.println("------木有权限-----");
			e.printStackTrace();
			return null;
		}
	}
	
	private static final String SEP1 = "#";
	public static String ListToString(List<?> list) {  
        StringBuffer sb = new StringBuffer();  
        if (list != null && list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {  
                if (list.get(i) == null || list.get(i) == "") {  
                    continue;  
                }  
                // 如果值是list类型则调用自�?  
                if (list.get(i) instanceof List) {  
                    sb.append(ListToString((List<?>) list.get(i)));  
                    sb.append(SEP1);  
                } else {  
                    sb.append(list.get(i));  
                    sb.append(SEP1);  
                }  
            }  
        }  
        return "L" + sb.toString();  
    } 
}
