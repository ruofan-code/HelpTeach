package com.example.demo.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.entity.Course;
import com.example.demo.dao.entity.HomeworkStudent;
import com.example.demo.dao.entity.StudentCourse;
import com.example.demo.dao.entity.User;
import com.example.demo.dao.mapper.CourseMapper;
import com.example.demo.service.ICourseService;
import com.example.demo.service.IHomeworkStudentService;
import com.example.demo.service.IStudentCourseService;
import com.example.demo.service.IUserService;
import com.example.demo.dao.web.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 若凡
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    java.lang.String CourseID;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private IStudentCourseService iStudentCourseService;
    @Autowired
    private IUserService iUserService;
    private Object String;

    @Autowired
    private IStudentCourseService studentCourseService;


    /**
     * @description
     * 获取教师课程列表
     * @return
     * @author 若凡
     * @date 2020/7/20 10:32
     */
    @RequestMapping("/getTeacherCourseList")
    public WebResponse getTeacherCourseList(HttpServletRequest request,String page,String limit){
        HttpSession session = request.getSession();
        String id = session.getAttribute("id").toString();
        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("teacher_id",id);
        Page<Course> coursePage= new Page<>(Integer.parseInt(page), Integer.parseInt(limit));

        IPage<Course> courseIPage = iCourseService.page(coursePage,queryWrapper);
        List<Course> list = iCourseService.list();
        System.out.println(list);
        WebResponse webResponse=new WebResponse();
        webResponse.setData(courseIPage.getRecords());
        webResponse.setCount(list.size());
        webResponse.setCode(0);
        webResponse.setMsg("success");
        return webResponse;
    }




    /**
     * @description
     * 老师创建课程
     * @return
     * @author 若凡
     * @date 2020/7/20 10:59
     */
    @PostMapping("/teacherCreateCourse")
    public WebResponse addcourse(HttpServletRequest request,String name) throws IOException {
        HttpSession session = request.getSession();
        String id = session.getAttribute("id").toString();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        User one = iUserService.getOne(queryWrapper);

        Course course = new Course();
        course.setId(UUID.randomUUID().toString());
        course.setName(name);
        course.setTeacherId(id);
        course.setTeacherName(one.getName());
        System.out.println(course);
        iCourseService.save(course);
        WebResponse webResponse=new WebResponse();
        webResponse.setMsg("添加课程成功");
        webResponse.setCount(0);
        return webResponse;
    }

    /**
     * @description
     * 老师删除课程
     * @return
     * @author 若凡
     * @date 2020/7/20 11:24
     */
    @PostMapping("/teacherDeleteCourse")
    public WebResponse deletecourse(String id){
        int delete = courseMapper.deleteById(id);
        WebResponse webResponse=new WebResponse();
        if(delete==1){
            webResponse.setCode(0);
        }else {
            webResponse.setCode(1);
        }
        return webResponse;
    }





    @Autowired
    IHomeworkStudentService iHomeworkStudentService;
    String Classid,Studentid,Id;


    //学生作业详细页面
    @RequestMapping("/studenttaskdetail")
    public String studenttaskdetail(HttpServletRequest request){
        String id=request.getParameter("id");
        Id=id;
        System.out.println(Id);
        return "/student/studenttaskdetail";
    }
    @RequestMapping("/studenttaskdetailmsg")
    @ResponseBody
    public WebResponse studentdetailmsg(){
        QueryWrapper<HomeworkStudent> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",Id);

        List<HomeworkStudent> allstudenttask = iHomeworkStudentService.list();
        HomeworkStudent datatest = allstudenttask.get(0);

        Object obj = JSONArray.toJSON(datatest);
        System.out.println(obj);

        WebResponse webResponse=new WebResponse();
        webResponse.setData(obj);
        webResponse.setCode(0);
        webResponse.setMsg("success");
        return webResponse;
    }


    /**
     * @description 
     * 获取学生课程列表
     * @return
     * @author 若凡
     * @date 2020/7/20 17:30
     */
    @RequestMapping("/getCourseByStuId")
    public WebResponse getCourseByStuId(HttpServletRequest request,String page, String limit){
        HttpSession session = request.getSession();
        String id = session.getAttribute("id").toString();

        QueryWrapper<StudentCourse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",id);
        Page<StudentCourse> coursePage= new Page<>(Integer.parseInt(page), Integer.parseInt(limit));
        IPage<StudentCourse> courseIPage = studentCourseService.page(coursePage,queryWrapper);
        List<StudentCourse> list = studentCourseService.list(queryWrapper);
        WebResponse webResponse=new WebResponse();
        webResponse.setData(courseIPage.getRecords());
        webResponse.setCount(list.size());
        webResponse.setCode(0);
        webResponse.setMsg("success");
        return webResponse;
    }
}

