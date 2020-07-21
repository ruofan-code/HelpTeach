package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.entity.StudentCourse;
import com.example.demo.service.IStudentCourseService;
import com.example.demo.dao.web.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/student-course")
public class StudentCourseController {
    @Autowired
    private IStudentCourseService studentCourseService;

    @GetMapping("/getCourseStudentList")
    public WebResponse getCourseStudentList(HttpServletRequest request,String page, String limit){
        //先根据所有的courseid找到与之对应的studentid
        //循环从user中找到所有与之对应的user信息 拼接为data
        String courseId = request.getSession().getAttribute("courseId").toString();
        QueryWrapper<StudentCourse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        Page<StudentCourse> studentCoursePage= new Page<>(Integer.parseInt(page), Integer.parseInt(limit));

        IPage<StudentCourse> studentcourseIPage = studentCourseService.page(studentCoursePage,queryWrapper);
        List<StudentCourse> allstudent = studentCourseService.list(queryWrapper);

        WebResponse webResponse=new WebResponse();
        webResponse.setData(studentcourseIPage.getRecords());
        webResponse.setCount(allstudent.size());
        webResponse.setCode(0);
        webResponse.setMsg("success");
        return webResponse;
    }
}

