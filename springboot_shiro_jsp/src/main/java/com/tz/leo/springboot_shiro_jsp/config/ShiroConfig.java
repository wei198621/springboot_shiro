package com.tz.leo.springboot_shiro_jsp.config;

import com.tz.leo.springboot_shiro_jsp.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * Author: tz_wl
 * Date: 2020/7/31 15:22
 * Content:
 */
@Configuration
public class ShiroConfig {

    //1.创建shiroFilter  //负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);                //给filter设置安全管理器

        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");

        HashMap<String, String> map = new HashMap<>();
        map.put("/user/login","anon");//anon 设置为公共资源  放行资源放在下面
        map.put("/user/register","anon");//anon 设置为公共资源  放行资源放在下面
        map.put("/register.jsp","anon");//anon 设置为公共资源  放行资源放在下面
        map.put("/login.jsp","anon");//anon 设置为公共资源  放行资源放在下面
       // map.put("/user/getImage","anon");
        //map.put("/**","authc");//authc 请求这个资源需要认证和授权
        //authc  是一个filter 后面再学习
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);  //给安全管理器设置
        return defaultWebSecurityManager;
    }



    /**
    *@Description:    3.创建自定义realm
    *@Param:
    *@return:
    *@date: 2020/8/1
    */
    @Bean
    public Realm getRealm(){

        CustomerRealm customerRealm = new CustomerRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);


        //开启缓存管理    EhCacheManager
        customerRealm.setCacheManager(new EhCacheManager());
        //开启缓存管理   RedisCacheManager
        //customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true);
        customerRealm.setAuthenticationCachingEnabled(true);
        customerRealm.setAuthenticationCacheName("authenticationCache");
        customerRealm.setAuthorizationCachingEnabled(true);
        customerRealm.setAuthorizationCacheName("authorizationCache");

        return customerRealm;
    }

}
