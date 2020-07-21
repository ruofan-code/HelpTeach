package com.example.demo.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String imain(){
        return "main";
    }
    /**
     * @return
     * @description 学生，教师首页
     * @author 若凡
     * @date 2020/7/17 14:36
     */
    @RequestMapping("/studentIndex")
    public String studentIndex() {
        return "student/index";
    }

    @RequestMapping("/teacherIndex")
    public String teacherIndex() {
        return "teacher/index";
    }

    @RequestMapping("/")
    public String login() {
        return "login";
    }


    @RequestMapping("/logout")
    public String loginOut(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @RequestMapping("studentNotice")
    public String studentNotice() {
        return "student/notice";
    }

    @RequestMapping("/studentNoticeDetail")
    public String studentNoticeDetail(String id, HttpSession session) {
        session.setAttribute("noticeId", id);
        return "student/notice_detail";
    }

    @RequestMapping("/teacherNotice")
    public String teacherNotice() {
        return "teacher/notice";
    }

    /**
     * @description 
     * 返回创建通知页面
     * @return
     * @author 若凡
     * @date 2020/7/21 9:10
     */
    @RequestMapping("/noticeCreate")
    public String teacherNoticeCreate(HttpServletRequest request) {
        request.getSession().setAttribute("noticeFileSrc", null);
        return "teacher/notice_create";
    }

    @RequestMapping("/noticeEdit")
    public String teacherNoticeEdit(String id, HttpSession session) {
        session.setAttribute("noticeId", id);
        return "teacher/notice_edit";
    }

    @RequestMapping("/teacherNoticeDetail")
    public String teacherNoticeDetail(String id, HttpSession session) {
        session.setAttribute("noticeId", id);
        return "teacher/notice_detail";
    }


    /**
     * @return
     * @description 返回教师课程列表
     * @author 若凡
     * @date 2020/7/20 10:24
     */
    @RequestMapping("/teachercourse")
    public String course() {
        System.out.println("所有课程信息");
        return "teacher/teacher_course";
    }

    /**
     * @return
     * @description 返回教师课程列表
     * @author 若凡
     * @date 2020/7/20 10:24
     */
    @RequestMapping("/course_create")
    public String course_create() {
        System.out.println("所有课程信息");
        return "teacher/course_create";
    }

    /**
     * @return
     * @description 教师课程下学生列表
     * @author 若凡
     * @date 2020/7/20 11:37
     */
    @RequestMapping("/course_student")
    public String course_student(HttpServletRequest request) {
        String course_id = request.getParameter("courseId");
        request.getSession().setAttribute("courseId", course_id);
        return "teacher/course_student";
    }

    /**
     * @return
     * @description返回课程作业列表
     * @author 若凡
     * @date 2020/7/20 12:58
     */
    @RequestMapping("/course_task")
    public String getallstudentsignin(HttpServletRequest request) {
        String courseId = request.getParameter("courseId");
        request.getSession().setAttribute("courseId", courseId);
//        String studentid = request.getParameter("studentid");
////        System.out.println(studentid);
//        Classid = classid;
//        Studentid = studentid;
        System.out.println("所有学生作业信息");
        return "teacher/course_task";
    }

    /**
     * @return
     * @description返回课程签到列表
     * @author 若凡
     * @date 2020/7/20 12:58
     */
    @RequestMapping("/course_signin")
    public String course_signin(HttpServletRequest request) {
        String courseId = request.getParameter("courseId");
        request.getSession().setAttribute("courseId", courseId);
//        String siginid=request.getParameter("siginid");
//        System.out.println(studentid);
        System.out.println("所有学生签到信息");
        return "teacher/course_signin";
    }

    /**
     * @return
     * @description 教师下课程下学生签到列表页面
     * @author 若凡
     * @date 2020/7/20 13:35
     */
    @RequestMapping("/student_signinList")
    public String student_signinList(HttpServletRequest request) {
        String courseId = request.getParameter("courseId");
        String studentId = request.getParameter("studentid");
        HttpSession session = request.getSession();
        session.setAttribute("studentId", studentId);
        session.setAttribute("courseId", courseId);
        return "teacher/student_signin";
    }

    /**
     * @return
     * @description 教师下课程下学生作业列表页面
     * @author 若凡
     * @date 2020/7/20 13:35
     */
    @RequestMapping("/student_taskList")
    public String student_task(HttpServletRequest request) {
        String courseId = request.getParameter("courseId");
        String studentId = request.getParameter("studentid");
        HttpSession session = request.getSession();
        session.setAttribute("studentId", studentId);
        session.setAttribute("courseId", courseId);
        return "teacher/student_task";
    }

    /**
     * @return
     * @description 返回課程新建作业页面
     * @author 若凡
     * @date 2020/7/20 14:00
     */
    @RequestMapping("/teacheraddtask")
    public String teacheraddtask(HttpServletRequest request) {
        String courseId = request.getParameter("classid");
        HttpSession session = request.getSession();
        session.setAttribute("courseId", courseId);
        session.setAttribute("teacherHomeworkFileSrc",null);
        session.setAttribute("teacherHomeworkFileName", null);
        return "teacher/teacher_add_task";
    }

    /**
     * @return
     * @description 返回課程作业查看页面
     * @author 若凡
     * @date 2020/7/20 14:00
     */
    @RequestMapping("/teachertaskdetail")
    public String gettaskdetail(HttpServletRequest request) {
        String taskid = request.getParameter("taskid");
        String courseId = request.getParameter("classid");
        HttpSession session = request.getSession();
        session.setAttribute("courseId", courseId);
        session.setAttribute("teacherHomewordId", taskid);
        return "teacher/teacher_task_detail";
    }

    /**
     * @return
     * @description 返回課程签到查看页面
     * @author 若凡
     * @date 2020/7/20 14:00
     */
    @RequestMapping("/signupTeacherDetail")
    public String signupTeacherDetail(HttpServletRequest request, String id) {
        HttpSession session = request.getSession();
        session.setAttribute("teacherSignupId", id);
        return "teacher/teacher_signup_detail";
    }

    /**
     * @return
     * @description 返回学生课程管理页面
     * @author 若凡
     * @date 2020/7/20 17:28
     */
    @RequestMapping("/studentCourse")
    public String studenCourse() {
        return "student/student_course";
    }

    /**
     * @return
     * @description 返回学生课程签到和作业列表
     * @author 若凡
     * @date 2020/7/20 17:40
     */
    @RequestMapping("/stuHomework")
    public String stuHomework(HttpServletRequest request,String courseId) {
        HttpSession session = request.getSession();
        session.setAttribute("courseId", courseId);
        return "student/student_homework";
    }

    @RequestMapping("/signupStudent")
    public String signupStudent(HttpServletRequest request,String courseId) {
        HttpSession session = request.getSession();
        session.setAttribute("courseId", courseId);
        return "student/student_signup";
    }

    @RequestMapping("/studentTeacherHomeworkDetail")
    public String studentTeacherHomeworkDetail(HttpServletRequest request,String id){
        HttpSession session = request.getSession();
        session.setAttribute("studentTeacherHomeworkId", id);
        return "student/teacher_homework_detail";
    }

}
