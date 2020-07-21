package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description
 * 自定义认证
 * @author 若凡
 * @date 2020/2/21 21:21
 */
@Component
public class LoginValidateAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;


    //解密用的
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取输入的用户名
        String username = authentication.getName();
        //获取输入的明文
        String password = (String) authentication.getCredentials();

//        User user = (User) userService.loadUserByUsername(username);

        UserDetails userDetails = userService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password,passwordEncoder.encode(userDetails.getPassword()))){
            throw new BadCredentialsException("输入密码错误!");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> aClass) {
        //确保authentication能转成该类
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
