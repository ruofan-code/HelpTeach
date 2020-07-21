package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.entity.Course;
import com.example.demo.dao.entity.SignupStudent;
import com.example.demo.dao.entity.SignupTeacher;
import com.example.demo.service.ICourseService;
import com.example.demo.service.ISignupStudentService;
import com.example.demo.service.ISignupTeacherService;
import com.example.demo.utils.Uuid;
import com.example.demo.dao.web.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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
@RequestMapping("/signup-teacher")
public class SignupTeacherController {
    @Autowired
    private ISignupTeacherService iSignupTeacherService;

    @Autowired
    private ISignupStudentService signupStudentService;

    @Autowired
    private ICourseService courseService;
    private Object String;
    /**
     * @description 
     * 获取课程教师签到列表
     * @return
     * @author 若凡
     * @date 2020/7/20 13:24
     */
    @RequestMapping("/classsigninlist")
    public WebResponse getclasssigninlist(HttpServletRequest request,String page,String limit){
//        System.out.println(Classid);
        HttpSession session = request.getSession();
        String courseId = session.getAttribute("courseId").toString();
        String teacherId = session.getAttribute("id").toString();
        QueryWrapper<SignupTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("teacher_id",teacherId);
        Page<SignupTeacher> signupStudentPage= new Page<>(Integer.parseInt(page), Integer.parseInt(limit));

        IPage<SignupTeacher> signupTeacherIPage = iSignupTeacherService.page(signupStudentPage,queryWrapper);
        List<SignupTeacher> signupteacher = iSignupTeacherService.list(queryWrapper);
        WebResponse webResponse = new WebResponse();
        webResponse.setData(signupTeacherIPage.getRecords());
        webResponse.setMsg("success");
        webResponse.setCode(0);
        webResponse.setCount(signupteacher.size());
        return webResponse;
    }

    /**
     * @description 
     * 老师添加签到
     * @return
     * @author 若凡
     * @date 2020/7/20 16:59
     */
    //teacherid写死了 ”1“
    @RequestMapping("/addsignin")
    public WebResponse addsignin(HttpServletRequest request,@RequestBody String json){
        HttpSession session = request.getSession();
        String courseId = session.getAttribute("courseId").toString();

        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        System.out.println(courseId);
        queryWrapper.eq("id",courseId);
        Course course=courseService.getOne(queryWrapper);

        JSONObject jo = (JSONObject) JSONObject.parse(json);
        Object addtime=jo.getObject("datatime", (TypeReference) String);
        long dataadd=Uuid.getSecond((java.lang.String) addtime);

        SignupTeacher signupTeacher=new SignupTeacher();
        signupTeacher.setId(Uuid.getUuid());
        signupTeacher.setTeacherId(course.getTeacherId());
        signupTeacher.setTeacherName(course.getTeacherName());
        signupTeacher.setCourseId(courseId);
        signupTeacher.setCourseName(course.getName());
        LocalDateTime xianzai=LocalDateTime.now();
        signupTeacher.setStartTime(xianzai);
        signupTeacher.setEndTime(xianzai.plusSeconds(dataadd));
        System.out.println(xianzai.plusSeconds(dataadd));

        boolean insert=iSignupTeacherService.save(signupTeacher);
        String msg;
        if(insert){
            msg="添加签到成功";
        }else {
            msg="添加签到失败";
        }
        WebResponse webResponse=new WebResponse();
        webResponse.setCode(0);
        webResponse.setMsg(msg);
        return webResponse;
    }


    /**
     * @return 三级列表，课程下，签到下，签到情况
     * @description
     * @author 若凡
     * @date 2020/7/20 14:50
     */
    @GetMapping("teacherSignupStudentList")
    public WebResponse teacherSignupStudentList(HttpServletRequest request,String limit,String page){
        HttpSession session = request.getSession();
        String teacherSignupId = session.getAttribute("teacherSignupId").toString();


        QueryWrapper<SignupStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("signup_teacher_id",teacherSignupId);
        List<SignupStudent> tasklist = signupStudentService.list(queryWrapper);

        Page<SignupStudent> signupStudentPage = new Page<>(Integer.parseInt(page), Integer.parseInt(limit));

        IPage<SignupStudent> signupStudentIPage = signupStudentService.page(signupStudentPage, queryWrapper);

        WebResponse webResponse = new WebResponse();
        webResponse.setData(signupStudentIPage.getRecords());
        webResponse.setCount(tasklist.size());
        webResponse.setCode(0);
        webResponse.setMsg("success");
        return webResponse;
    }
}

