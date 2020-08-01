package com.tz.leo.springboot_shiro;

import com.tz.leo.springboot_shiro.shiro.CustomerRealm02WithMd5Salt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Author: tz_wl
 * Date: 2020/7/31 13:18
 * Content:
 */
public class SimpleShiro3Realm02Withmd5SaltTest {

    @Test
    public void test(){

        System.out.println("Hello World!");

        String salt = "Q4F%";
       // System.out.println("salt is "+ salt);
       // System.out.println(" salt bytes is "+ org.apache.shiro.util.ByteSource.Util.bytes(salt));


        //创建securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();


        //defaultSecurityManager.setRealm(new IniRealm("classpath:shiro.ini"));   //type1  设置为iniRealm 从ini文件取数据
        //defaultSecurityManager.setRealm(new CustomerRealm01());                   //type2  设置为自定义realm获取认证数据
        CustomerRealm02WithMd5Salt customerRealm = new CustomerRealm02WithMd5Salt();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);                              //设置散列次数
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        defaultSecurityManager.setRealm(customerRealm);                          //type3  设置MD5加密 及 散列次数 定义 Realm



        //将安装工具类中设置默认安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //创建token令牌   xiaochen=123
        UsernamePasswordToken token = new UsernamePasswordToken("xiaochen", "123");

        try {
            subject.login(token);
            System.out.println(" 登录成功  003 ");
        } catch (UnknownAccountException e) {
            System.out.println(" 未知用户  003 ");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            System.out.println(" 密码错误  003 ");
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }



    }
}
