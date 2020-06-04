package com.example.SSHdemo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 如果只实现一个WebSecurityConfigurerAdapter然后重写一下configure方法，效果会默认使用spring security的登录页,
 * 以及项目启动时后台会打印出一个默认的密码，然后使用任意账号就可以进行登录访问指定的资源.
 *
 *  如果想要使用自己的登录页 并且用户名密码是自己数据库中的, 需要重写configure()方法.
 */
/** 该类的三个注解分别是标识该类是配置类、开启 Security 服务、开启全局 Securtiy 注解 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    SecurityUserDetailsService securityUserDetailsService;

    /**
     * 默认Security自带登录页面，这里项目一般会自定义登录页 与 UserDetailsService 用户名密码校验
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //以下是表单登录进行身份认证最简单的登陆环境
        http.formLogin() //表单登陆
                .loginPage("/index.html") //指定登陆页面
                //登陆页面提交的页面, 开始使用UsernamePasswordAuthenticationFilter过滤器处理请求
                .loginProcessingUrl("/login")
                //定义登录时，用户名和用户密码的 key，默认为 username 和 password
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .defaultSuccessUrl("/secureMain")
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .and()
                .authorizeRequests() //下面的都是授权的配置
                .anyRequest() //任何请求
                .authenticated() //访问任何资源都需要身份认证
                .and()
                .csrf().disable();//关闭跨站请求伪造攻击拦截
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹, 可以对静态资源放行
        web.ignoring().antMatchers("/index.html","/**/**.css","/**/**.js","/**/**.jpg","/**/**.png");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(securityUserDetailsService);
//    }

    /**
     * 介绍：
     * springBoot2.x引入的security版本是5.x的, 这个5.x版本开始需要提供一个PasswordEncoder实例, 不然就会报错
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
