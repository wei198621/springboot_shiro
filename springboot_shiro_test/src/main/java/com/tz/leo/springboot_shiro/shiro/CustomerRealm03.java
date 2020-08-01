package com.tz.leo.springboot_shiro.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Author: tz_wl
 * Date: 2020/7/31 14:44
 * Content:
 */
public class CustomerRealm03 extends AuthorizingRealm {
    /**
    *@Description: 授权方法
    *@Param:
    *@return:
    *@date: 2020/7/31
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String primaryPrincipal = principals.getPrimaryPrincipal().toString();
        System.out.println("=====doGetAuthorizationInfo=====   primaryPrincipal = " + primaryPrincipal);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addStringPermission("user:update:*");
        simpleAuthorizationInfo.addStringPermission("product:*:*");
        return simpleAuthorizationInfo;
    }

    /**
    *@Description: 认证 
    *@Param: 
    *@return: 
    *@date: 2020/7/31
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = token.getPrincipal().toString();
        if("xiaochen".equals(principal)){
            String password = "3c88b338102c1a343bcb88cd3878758e";
            String salt = "Q4F%";
            return new SimpleAuthenticationInfo(principal,password, ByteSource.Util.bytes(salt),this.getName());
        }
        return null;
    }
}
