package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.entity.SignupStudent;
import com.example.demo.dao.entity.SignupTeacher;
import com.example.demo.dao.entity.User;
import com.example.demo.dao.web.WebSignupTeacher;
import com.example.demo.service.ISignupStudentService;
import com.example.demo.service.ISignupTeacherService;
import com.example.demo.service.IUserService;
import com.example.demo.dao.web.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 若凡
 * @since 2020-07-19
 */
@RestController
@RequestMapping("/signup-student")
public class SignupStudentController {
    @Autowired
    ISignupStudentService iSignupStudentService;

    @Autowired
    private ISignupTeacherService signupTeacherService;

    @Autowired
    private IUserService userService;

    /**
     * @return
     * @description 返回教师下课程下学生下签到列表
     * @author 若凡
     * @date 2020/7/20 13:49
     */
    @RequestMapping("/studentsigninlist")
    public WebResponse studentsiginlist(HttpServletRequest request, String page, String limit) {
        HttpSession session = request.getSession();
        String courseId = session.getAttribute("courseId").toString();
        String studentId = session.getAttribute("studentId").toString();

        QueryWrapper<SignupStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("student_id", studentId);
        Page<SignupStudent> studentCoursePage = new Page<>(Integer.parseInt(page), Integer.parseInt(limit));

        IPage<SignupStudent> studentcourseIPage = iSignupStudentService.page(studentCoursePage, queryWrapper);
        List<SignupStudent> allstudent = iSignupStudentService.list(queryWrapper);

        WebResponse webResponse = new WebResponse();
        webResponse.setData(studentcourseIPage.getRecords());
        webResponse.setCount(allstudent.size());
        webResponse.setCode(0);
        webResponse.setMsg("success");
        return webResponse;
    }


    /**
     * @return
     * @description 学生查看课程下签到情况
     * @author 若凡
     * @date 2020/7/20 17:49
     */
    //点击查看签到按钮
    @RequestMapping("/getSignByCourseId")
    @ResponseBody
    public WebResponse getDetailByCourseId(HttpServletRequest request, String page, String limit) {
        HttpSession session = request.getSession();
        String courseId = session.getAttribute("courseId").toString();
//        String teacherId = session.getAttribute("id").toString();
        QueryWrapper<SignupTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
//        queryWrapper.eq("teacher_id",teacherId);
        Page<SignupTeacher> signupTeacherPage = new Page<>(Integer.parseInt(page), Integer.parseInt(limit));

        IPage<SignupTeacher> signupTeacherIPage = signupTeacherService.page(signupTeacherPage, queryWrapper);

        List<WebSignupTeacher> webSignupTeachers = new ArrayList<>();
        WebSignupTeacher webSignupTeacher;
        List<SignupTeacher> records = signupTeacherIPage.getRecords();
        QueryWrapper<SignupStudent> queryWrapper1;
        for (SignupTeacher record : records) {
            webSignupTeacher = new WebSignupTeacher(record);
            queryWrapper1 = new QueryWrapper<>();
//            webSignupTeacher.setStatus("未签到");

            queryWrapper1.eq("signup_teacher_id",record.getId());
            SignupStudent one = iSignupStudentService.getOne(queryWrapper1);
            if (one!=null){
//                webSignupTeacher.setStatus(one.getStatus());
                webSignupTeacher.setSignupTime(one.getSignTime());
            }
            webSignupTeachers.add(webSignupTeacher);
        }

        List<SignupTeacher> signupteacher = signupTeacherService.list(queryWrapper);
        WebResponse webResponse = new WebResponse();
        webResponse.setData(webSignupTeachers);
        webResponse.setMsg("success");
        webResponse.setCode(0);
        webResponse.setCount(signupteacher.size());
        return webResponse;
    }

    /**
     * @return
     * @description 学生进行签到
     * @author 若凡
     * @date 2020/7/20 19:35
     */
    @GetMapping("/studentSignup")
    public WebResponse studentSignup(HttpServletRequest request, String id) {
        WebResponse<Object> webResponse = new WebResponse<>();
        HttpSession session = request.getSession();
        String id1 = session.getAttribute("id").toString();
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", id1);
        User one = userService.getOne(queryWrapper1);


        QueryWrapper<SignupStudent> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("student_id", id1);
        queryWrapper2.eq("signup_teacher_id", id);
        SignupStudent one1 = iSignupStudentService.getOne(queryWrapper2);
        if (one1 != null) {
            webResponse.setCode(1);
            webResponse.setMsg("已经签到过了！");
            return webResponse;
        }


        QueryWrapper<SignupTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        SignupTeacher signupTeacher = signupTeacherService.getOne(queryWrapper);
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, signupTeacher.getEndTime());
        if (duration.toMillis() < 0) {
            webResponse.setCode(1);
            webResponse.setMsg("签到时间已过！");
            return webResponse;
        }
        SignupStudent signupStudent = new SignupStudent();
        signupStudent.setId(UUID.randomUUID().toString());
        signupStudent.setStudentId(one.getId());
        signupStudent.setStudentName(one.getName());
        signupStudent.setSignupTeacherId(id);
        signupStudent.setSignupTeacherName(signupTeacher.getTeacherName());
        signupStudent.setSignTime(now);
        signupStudent.setCourseId(signupTeacher.getCourseId());
        signupStudent.setCourseName(signupTeacher.getCourseName());

        iSignupStudentService.save(signupStudent);
        webResponse.setCode(0);
        webResponse.setMsg("签到成功！");
        return webResponse;

    }


}

