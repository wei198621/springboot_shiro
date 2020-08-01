package com.tz.leo.springboot_shiro_jsp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Author: tz_wl
 * Date: 2020/7/31 15:44
 * Content:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Perms implements Serializable {

    private String id;
    private String name;
    private String url;
}
