package com.luoshengsha.shiro;

import java.util.Properties;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * shiro配置
 * 
 * @author luoshengsha
 *
 *         2016年5月10日-下午6:46:24
 */
@Configuration
public class ShiroConfiguration {
	/**
	 * 获取自定义realm
	 * 
	 * @return
	 */
	@Bean(name = "ShiroRealm")
	public ShiroRealm getShiroRealm() {
		return new ShiroRealm();
	}

	/**
	 * 自定义security manager
	 * 
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(ShiroRealm shiroDbRealm) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(getShiroRealm());
		return dwsm;
	}
	
	@Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager  
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        
        return shiroFilterFactoryBean;
    }
	
	@Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {  
        return new LifecycleBeanPostProcessor();  
    }
	
	@Bean  
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();  
        daap.setProxyTargetClass(true);
        return daap;  
    }
	
	@Bean  
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {  
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();  
        aasa.setSecurityManager(getDefaultWebSecurityManager(getShiroRealm()));  
        return new AuthorizationAttributeSourceAdvisor();  
    }
	
	@Bean
	public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver sm = new SimpleMappingExceptionResolver();
		
		Properties mappings = new Properties();
		mappings.setProperty("org.apache.shiro.authz.UnauthorizedException", "/login?name=luoshengsha&password=123456");
		mappings.setProperty("org.apache.shiro.authz.UnauthenticatedException", "");
		sm.setExceptionMappings(mappings);
		
		return sm;
	}
}
