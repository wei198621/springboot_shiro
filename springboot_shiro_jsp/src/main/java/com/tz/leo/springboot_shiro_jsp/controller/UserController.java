package com.tz.leo.springboot_shiro_jsp.controller;

import com.tz.leo.springboot_shiro_jsp.entity.User;
import com.tz.leo.springboot_shiro_jsp.service.UserService;
import com.tz.leo.springboot_shiro_jsp.utils.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author: tz_wl
 * Date: 2020/7/31 16:35
 * Content:
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        String code = VerifyCodeUtils.generateVerifyCode(4);   //生成验证码
        session.setAttribute("code",code);                           //验证码放入session
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtils.outputImage(220,60,os,code);              //验证码存入图片
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    public String register(User user){
        try {
            userService.register(user);
            return "redirect:/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();  //退出登录
        return "redirect:/login.jsp";
    }


    /**
     * 用户登录
     * @param username
     * @param password
     * @param code
     * @param session
     * @return
     */
    @RequestMapping("login")
    public String login(String username,String password,String code,HttpSession session){
        try {
            String  codes = (String) session.getAttribute("code");
            if(codes.equalsIgnoreCase(code)){
                //  ShiroConfig  创建安全管理器  DefaultWebSecurityManager  ,会自动注入到安全管理器SecurityUtils
                //
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);
                return "redirect:/index.jsp";
            }else {
                throw new RuntimeException("验证码错误");
            }
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return "redirect:/login.jsp";
    }

}
