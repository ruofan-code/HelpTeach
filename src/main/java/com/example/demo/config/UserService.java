package com.example.demo.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 若凡
 * @description 实现加载用户名称的方法，目的是为了获取用户的信息，以便接下来的认证
 * @date 2020/2/21 21:20
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IUserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<com.example.demo.dao.entity.User> queryWrapper1 = new QueryWrapper<>();

        if (request.getSession().getAttribute("id") != null && !request.getSession().getAttribute("username").equals(username)) {
            throw new LockedException(request.getSession().getAttribute("username") + "已登录,请登录该账户并注销");
        }

        queryWrapper1.eq("username", username);

        com.example.demo.dao.entity.User user = userService.getOne(queryWrapper1);

        if (user == null)
            throw new UsernameNotFoundException("不存在该用户!");
        else {
            if (user.getRoleCode() == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("id", user.getId());
                session.setAttribute("roleCode", "1");
                session.setAttribute("username", username);
                return new User(username, user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("teacher"));
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("id", user.getId());
                session.setAttribute("roleCode", "2");
                session.setAttribute("username", username);
                return new User(username, user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("student"));
            }
        }


    }
}
