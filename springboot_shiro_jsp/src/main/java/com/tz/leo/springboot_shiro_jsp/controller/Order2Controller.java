package com.tz.leo.springboot_shiro_jsp.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Author: tz_wl
 * Date: 2020/8/1 16:08
 * Content:
 */
@Controller
@RequestMapping("order2")
public class Order2Controller {


    @RequestMapping("save")
    public String save(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            System.out.println("subject has admin role save success ");
        }else{
            System.out.println("subject does not have role admein save failed ");
        }
        return "redirect:/index.jsp";
    }
}
