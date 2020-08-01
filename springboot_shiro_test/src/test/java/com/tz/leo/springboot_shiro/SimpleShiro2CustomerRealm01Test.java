package com.tz.leo.springboot_shiro;

import com.tz.leo.springboot_shiro.shiro.CustomerRealm01;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Author: tz_wl
 * Date: 2020/7/31 13:18
 * Content:
 */
public class SimpleShiro2CustomerRealm01Test {

    /**
    *@Description: CustomerRealm01 自定义Realm
    *@Param: 
    *@return: 
    *@date: 2020/7/31
    */
    @Test
    public void test(){

        System.out.println("Hello World!");

        //创建securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();


        //defaultSecurityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        defaultSecurityManager.setRealm(new CustomerRealm01());  //设置为自定义realm获取认证数据

        //将安装工具类中设置默认安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //创建token令牌   xiaochen=123
        UsernamePasswordToken token = new UsernamePasswordToken("xiaochen", "123");

        try {
            subject.login(token);
            System.out.println(" 登录成功  002 ");
        } catch (UnknownAccountException e) {
            System.out.println(" 未知用户  002 ");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            System.out.println(" 密码错误  002 ");
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }



    }
}
