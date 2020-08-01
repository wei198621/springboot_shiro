package com.tz.leo.springboot_shiro_jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ShiroApp
{
    public static void main( String[] args )
    {
        System.out.println( "SpringBootApplication run start " );
        SpringApplication.run(ShiroApp.class,args);
    }
}
