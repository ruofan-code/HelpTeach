package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.entity.*;
import com.example.demo.dao.web.WebHomeworkTeacher;
import com.example.demo.dao.web.WebSignupTeacher;
import com.example.demo.service.IHomeworkStudentService;
import com.example.demo.service.IHomeworkTeacherService;
import com.example.demo.dao.web.WebResponse;
import com.example.demo.service.IUserService;
import com.example.demo.utils.FastdfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 若凡
 * @since 2020-07-19
 */
@RestController
@RequestMapping("/homework-student")
public class HomeworkStudentController {
    @Autowired
    private IHomeworkStudentService homeworkStudentService;

    @Autowired
    private IHomeworkTeacherService homeworkTeacherService;

    @Autowired
    private FastdfsUtil fastdfsUtil;

    @Autowired
    private IUserService userService;


    /**
     * @description
     * 教师下课程下学生下作业列表
     * @return
     * @author 若凡
     * @date 2020/7/20 13:47
     */
    @RequestMapping("/studenttasklist")
    @ResponseBody
    public WebResponse studenttasklist(HttpServletRequest request,String page, String limit){
        HttpSession session = request.getSession();
        String courseId = session.getAttribute("courseId").toString();
        String studentId = session.getAttribute("studentId").toString();
        QueryWrapper<HomeworkStudent> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("student_id",studentId);
        Page<HomeworkStudent> studentCoursePage= new Page<>(Integer.parseInt(page), Integer.parseInt(limit));

        IPage<HomeworkStudent> studentcourseIPage = homeworkStudentService.page(studentCoursePage,queryWrapper);
        List<HomeworkStudent> allstudenttask = homeworkStudentService.list(queryWrapper);
        System.out.println(allstudenttask);

        WebResponse webResponse=new WebResponse();
        webResponse.setData(studentcourseIPage.getRecords());
        webResponse.setCount(allstudenttask.size());
        webResponse.setCode(0);
        webResponse.setMsg("success");
        return webResponse;
    }


    /**
     * @description
     * 获取学生课程下作业
     * @return
     * @author 若凡
     * @date 2020/7/20 17:57
     */
    //点击查看该课程作业情况
    @RequestMapping("/getHomeworkByCourseId")
    @ResponseBody
    public WebResponse getHomeworkByCourseId(HttpServletRequest request,String page,String limit){
        HttpSession session = request.getSession();
        String courseId = session.getAttribute("courseId").toString();
//        String teacherId = session.getAttribute("id").toString();
        QueryWrapper<HomeworkTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
//        queryWrapper.eq("teacher_id",teacherId);
        Page<HomeworkTeacher> homeworkTeacherPage= new Page<>(Integer.parseInt(page), Integer.parseInt(limit));
        IPage<HomeworkTeacher> homeworkTeacherIPage = homeworkTeacherService.page(homeworkTeacherPage,queryWrapper);


        List<WebHomeworkTeacher> webHomeworkTeachers = new ArrayList<>();
        WebHomeworkTeacher webHomeworkTeacher;
        List<HomeworkTeacher> records = homeworkTeacherIPage.getRecords();
        QueryWrapper<HomeworkStudent> queryWrapper1;
        for (HomeworkTeacher record : records) {
            webHomeworkTeacher = new WebHomeworkTeacher(record);
            queryWrapper1 = new QueryWrapper<>();
//            webSignupTeacher.setStatus("未签到");

            queryWrapper1.eq("homework_teacher_id",record.getId());
            HomeworkStudent one = homeworkStudentService.getOne(queryWrapper1);
            if (one!=null){
//                webSignupTeacher.setStatus(one.getStatus());
                webHomeworkTeacher.setFinishTime(one.getFinishTime());
            }
            webHomeworkTeachers.add(webHomeworkTeacher);
        }


        List<HomeworkTeacher> homeworkTeachers = homeworkTeacherService.list(queryWrapper);
        WebResponse webResponse = new WebResponse();
        webResponse.setData(webHomeworkTeachers);
        webResponse.setMsg("success");
        webResponse.setCode(0);
        webResponse.setCount(homeworkTeachers.size());
        return webResponse;
    }


    /**
     * @return
     * @description 学生作业上传
     * @author 若凡
     * @date 2020/7/21 8:58
     */
    @PostMapping("/upload")
    public WebResponse upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
//        String id = request.getSession().getAttribute("id").toString();
        String id = request.getSession().getAttribute("studentHomeworkSubmitId").toString();
        String studentId = request.getSession().getAttribute("id").toString();

        QueryWrapper<HomeworkTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        HomeworkTeacher one = homeworkTeacherService.getOne(queryWrapper);
        byte[] bytes = file.getBytes();
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = file.getName();
        long fileSize = file.getSize();
        System.out.println(originalFileName + "==========" + fileName + "===========" + fileSize + "===============" + extension + "====" + bytes.length);
        String filesrc = fastdfsUtil.uploadFile(bytes, fileSize, extension);
//        request.getSession().setAttribute("studentHomeworkSubmitFileSrc", filesrc);
//        request.getSession().setAttribute("teacherHomeworkFileName", originalFileName);

        QueryWrapper<HomeworkStudent> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("student_id",studentId);
        queryWrapper1.eq("homework_teacher_id",id);
        HomeworkStudent homeworkStudent = homeworkStudentService.getOne(queryWrapper1);
        if (homeworkStudent!=null){
            homeworkStudent.setFilesrc(filesrc);
            homeworkStudent.setFinishTime(LocalDateTime.now());
            homeworkStudentService.updateById(homeworkStudent);
        }else {
            homeworkStudent = new HomeworkStudent();
            homeworkStudent.setId(UUID.randomUUID().toString());
            homeworkStudent.setStudentId(studentId);
            QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("id",studentId);
            User user = userService.getOne(queryWrapper2);
            homeworkStudent.setStudentName(user.getName());
            homeworkStudent.setHomeworkTeacherId(id);
            homeworkStudent.setHomeworkTeacherName(one.getTeacherName());
            homeworkStudent.setCourseId(one.getCourseId());
            homeworkStudent.setCourseName(one.getCourseName());
            homeworkStudent.setFilesrc(filesrc);
            homeworkStudent.setFinishTime(LocalDateTime.now());
            homeworkStudentService.save(homeworkStudent);
        }

        WebResponse webResponse = new WebResponse();
        webResponse.setData(originalFileName);
        return webResponse;
    }

    @PostMapping("/submitTeacherHomeworkId")
    public void submitTeacherHomeworkId(HttpServletRequest request,String id){
        //保存即将上传的教师作业id
        request.getSession().setAttribute("studentHomeworkSubmitId", id);
    }


    @GetMapping("/teacherGetStudentHomeworkById")
    public void teacherGetStudentHomeworkById(HttpServletResponse response,String id) throws IOException {
        //        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",id);
//        Notice one = noticeService.getOne(queryWrapper);
//        String teacherHomeworkFileSrc = request.getSession().getAttribute("teacherHomeworkFileSrc").toString();
//        Object teacherHomeworkFileName = request.getSession().getAttribute("teacherHomeworkFileName");
        QueryWrapper<HomeworkStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        HomeworkStudent homeworkStudent = homeworkStudentService.getOne(queryWrapper);
        byte[] bytes = fastdfsUtil.downloadFile(homeworkStudent.getFilesrc());
        // 这里只是为了整合fastdfs，所以写死了文件格式。需要在上传的时候保存文件名。下载的时候使用对应的格式
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(homeworkStudent.getStudentName()+".rar", "UTF-8"));
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", id);
//        Notice one = noticeService.getOne(queryWrapper);
//        WebResponse webResponse = new WebResponse();
//        webResponse.setData(one.getFilesrc());
//        return webResponse;
    }

}

