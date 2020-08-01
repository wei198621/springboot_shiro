package com.tz.leo.springboot_shiro_jsp.shiro.realms;

import com.tz.leo.springboot_shiro_jsp.entity.Perms;
import com.tz.leo.springboot_shiro_jsp.entity.User;
import com.tz.leo.springboot_shiro_jsp.service.UserService;
import com.tz.leo.springboot_shiro_jsp.shiro.salt.MyByteSource;
import com.tz.leo.springboot_shiro_jsp.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Author: tz_wl
 * Date: 2020/7/31 15:39
 * Content:
 *
 * 注意此处userService的写法
 * 可以通过两种方式获取 UserService 示例化对象
 * 1. 自定义UserService属性 ，通过Autowired 注解，实例化 userService为 UserServiceImpl 实例
 * -------方法1 用的是spring 方法
 * 2. 通过帮助类  ApplicationContextUtils.getBean 方法 获取  userService实例
 * -------方法2 用的是 通用方法
 */
public class CustomerRealm extends AuthorizingRealm {




    //@Autowired
    //private UserService userService;

    /**
    *@Description: 页面权限授权
    *@Param:
    *@return:
    *@date: 2020/8/1
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("调用授权验证: "+primaryPrincipal);
        //根据主身份信息获取角色 和 权限信息
        final UserService userService = (UserService)ApplicationContextUtils.getBean("userService");
        User user=userService.findRolesByUserName(primaryPrincipal);

        if(!CollectionUtils.isEmpty(user.getRoles())){
            final SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role->{
                simpleAuthorizationInfo.addRole(role.getName());
                List<Perms> perms = userService.findPermsByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(perms)){
                    perms.forEach(perm->{
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    /**
    *@Description: 用户认证
    *@Param:
    *@return:
    *@date: 2020/8/1
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String principal = token.getPrincipal().toString();

        //在工厂中获取service对象
        UserService userService=(UserService)ApplicationContextUtils.getBean("userService");
        User user = userService.findByUserName(principal);
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),
                    user.getPassword(),
                    new MyByteSource(user.getSalt()),
                    this.getName());
        }
        return null;
    }
}
