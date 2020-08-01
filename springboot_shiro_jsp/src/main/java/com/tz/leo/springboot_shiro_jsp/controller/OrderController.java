package com.tz.leo.springboot_shiro_jsp.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: tz_wl
 * Date: 2020/7/31 16:59
 * Content:
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @RequestMapping("save")
    @RequiresRoles(value = {"admin","user"})  ///用来判断角色  同时具有 admin user
    @RequiresPermissions("user:update:01") ///用来判断权限字符串
    public String save(){
        System.out.println("进入save方法");
        return "redirect:/index.jsp";
    }

}
