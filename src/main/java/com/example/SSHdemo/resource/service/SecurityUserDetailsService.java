package com.example.SSHdemo.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 获取UserDetails的服务类
 * spring security中对于用户的表示有自己的实体类相对应，它就是UserDetails
 * 提示，我们也可以自定义一个UserDetails，只需要实现UserDetails接口即可.
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    /**
     * 实现UserDetailsService接口进行用户姓名密码校验
     * (由于springboot2.x中security是5.x版本的，所以这里的密码是默认做了BCrypt加密的，就需要bean一个BCrypt)
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找用户信息
        com.example.SSHdemo.resource.entity.User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user: " + username + " is not found.");
        }
        //查询到的密码
        String pwd = user.getPassword();
        //密码进行bcrypt加密
        String cryptPwd = passwordEncoder.encode(pwd);
        // User(账号, 加密的密码, 权限);   注意：权限这里使用硬编码，获取用户的角色，实际项目需要从数据库获取
        // 权限格式为ROLE_XXX，是Spring Security规定的，不要乱起名字
        return new User(username,cryptPwd, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
