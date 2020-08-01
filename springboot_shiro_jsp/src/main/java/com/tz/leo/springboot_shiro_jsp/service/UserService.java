package com.tz.leo.springboot_shiro_jsp.service;

import com.tz.leo.springboot_shiro_jsp.entity.Perms;
import com.tz.leo.springboot_shiro_jsp.entity.User;

import java.util.List;

/**
 * Author: tz_wl
 * Date: 2020/7/31 16:30
 * Content:
 */
public interface UserService {

    //注册用户方法
    void register(User user);

    //根据用户名查询业务的方法
    User findByUserName(String username);

    //根据用户名查询所有角色
    User findRolesByUserName(String username);

    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String id);

}
