package com.tz.leo.springboot_shiro_jsp.dao;

import com.tz.leo.springboot_shiro_jsp.entity.Perms;
import com.tz.leo.springboot_shiro_jsp.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: tz_wl
 * Date: 2020/7/31 16:26
 * Content:
 */

@Mapper
public interface UserDAO {
    void save(User user);
    User findByUserName(String userName);
    //根据用户名查询所有角色----有问题吧
    User findRolesByUserName(String username);

    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String roleId);


}
