package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.entity.Role;
import com.example.demo.service.IRoleService;
import com.example.demo.dao.web.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 若凡
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/getlist")
    public WebResponse getlist(){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","kjjk");
        List<Role> list = roleService.list(queryWrapper);
        WebResponse<Object> webResponse = new WebResponse<>();
        webResponse.setData(list);
        webResponse.setCount(list.size());
        webResponse.setCode(0);
        return webResponse;
    }
}

