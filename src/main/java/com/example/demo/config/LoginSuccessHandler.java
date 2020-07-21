package com.example.demo.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * 登录成功类
 * @author 若凡
 * @date 2020/2/21 21:22
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //登录成功返回
        Map<String, Object> paramMap = new HashMap<>();
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getAuthorities().toString());
        if (authentication.getAuthorities().toString().equals("[teacher]")){
            paramMap.put("code", "1");
            paramMap.put("msg", "登录成功!");
        }else if (authentication.getAuthorities().toString().equals("[student]")){
            paramMap.put("code", "2");
            paramMap.put("msg", "登录成功!");
        }
//        else {
//            paramMap.put("code", "3");
//            paramMap.put("msg", "登录成功!");
//        }

        //设置返回请求头
        response.setContentType("application/json;charset=utf-8");
        //写出流
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(paramMap));
        out.flush();
        out.close();
    }

}
