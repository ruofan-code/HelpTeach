package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @description
 *  SpringSecurity配置类
 * @author 若凡
 * @date 2020/2/21 21:19
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //自定义认证
    @Resource
    private com.example.demo.config.LoginValidateAuthenticationProvider loginValidateAuthenticationProvider;

    //登录成功handler
    @Resource
    private com.example.demo.config.LoginSuccessHandler loginSuccessHandler;

    //登录失败handler
    @Resource
    private com.example.demo.config.LoginFailureHandler loginFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/login","/plugins/**","/res/**","/getPassword","/error/**").permitAll()
                .antMatchers("/teacherIndex/**").hasAuthority("teacher")
                .antMatchers("/studentIndex/**").hasAuthority("student")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/")//登录页面url
                .loginProcessingUrl("/user/login")//登录验证url
                .successHandler(loginSuccessHandler)//成功登录处理器
                .failureHandler(loginFailureHandler);//失败登录处理器




        //关闭csrf跨域攻击防御
//        http.csrf().disable();
        http.headers().frameOptions().disable();
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这里要设置自定义认证
        auth.authenticationProvider(loginValidateAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/plugins/**","/res/**");
        web.ignoring().antMatchers("/**/*.js", "/lang/*.json", "/**/*.css", "/**/*.js", "/**/*.map", "/**/*.html",
                "/**/*.png","/static/**");
    }

    /**
     * BCrypt加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
