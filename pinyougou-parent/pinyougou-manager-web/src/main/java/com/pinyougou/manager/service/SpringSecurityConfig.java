package com.pinyougou.manager.service;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2019/3/5 15:30
 *  配置类,取代SpringSecurity配置文件
 ****/
@Component
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    /***
    * 1.公开文件配置
    * */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/plugins/**","/img/**","/login.html","/403.html");
    }

    /*
    * 2.受访问限制配置
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/**").         //其他访问路径
                access("hasAnyRole('ADMIN')");  //必须登录后，角色为ADMIN才能访问

        //自定义登录配置
        http.formLogin().
                loginPage("/login.html").           //自定义登录页
                failureUrl("/login.html").          //登录失败跳转地址
                defaultSuccessUrl("/admin/index.html",true).     //登录成功跳转地址
                loginProcessingUrl("/login");       //登录处理地址

        //自定义登出
        http.logout().
                logoutUrl("/logout").               //登出处理地址
                logoutSuccessUrl("/login.html").    //登出成功后跳转地址
                invalidateHttpSession(true);         //让session无效


        //禁用CSRF
        http.csrf().disable();

        //一个账号只允许在一个地方登录
        http.sessionManagement().maximumSessions(1).expiredUrl("/login.html");

        //异常处理
        http.exceptionHandling().accessDeniedPage("/403.html");

        //iframe框架的启用
        http.headers().frameOptions().disable();
    }


    /*
    * 3.授权认证管理器
    * */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //创建授权账号和授权账号的角色信息
        auth.inMemoryAuthentication().
                withUser("admin").
                password("{noop}123456").
                roles("ADMIN");
    }
}
