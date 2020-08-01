package com.tz.leo.springboot_shiro_jsp.utils;

import java.util.Random;

/**
 * Author: tz_wl
 * Date: 2020/7/31 15:50
 * Content:
 */
public class SaltUtils {

    /**
     * 生成salt的静态方法
     * @param n
     * @return
     */
    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

  /*  public static void main(String[] args) {
        String salt = getSalt(8);
        System.out.println(salt);   //LuK!rM03     //Haf*N6^V
    }*/

}
