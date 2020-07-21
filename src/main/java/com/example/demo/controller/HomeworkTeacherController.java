package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.entity.Course;
import com.example.demo.dao.entity.HomeworkStudent;
import com.example.demo.dao.entity.HomeworkTeacher;
import com.example.demo.dao.entity.Notice;
import com.example.demo.dao.mapper.HomeworkTeacherMapper;
import com.example.demo.service.ICourseService;
import com.example.demo.service.IHomeworkStudentService;
import com.example.demo.service.IHomeworkTeacherService;
import com.example.demo.dao.web.WebResponse;
import com.example.demo.utils.FastdfsUtil;
import org.apache.xmlbeans.impl.store.Query;
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
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 若凡
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/homework-teacher")
public class HomeworkTeacherController {
    String Classid, Studentid;
    String DetailClassid, Taskid;
    String AddClassid;
    @Autowired
    private HomeworkTeacherMapper homeworkTeacherMapper;

    @Autowired
    private IHomeworkTeacherService homeworkTeacherService;

    @Autowired
    private IHomeworkStudentService homeworkStudentService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private FastdfsUtil fastdfsUtil;

    /**
     * @return
     * @description 返回课程教师作业
     * @author 若凡
     * @date 2020/7/20 13:29
     */
    @RequestMapping("/classtaskinlist")
    public WebResponse getclasssigninlist(HttpServletRequest request, String page, String limit) {
        HttpSession session = request.getSession();
        String courseId = session.getAttribute("courseId").toString();
        String teacherId = session.getAttribute("id").toString();

        QueryWrapper<HomeworkTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("teacher_id", teacherId);
        Page<HomeworkTeacher> classtaskPage = new Page<>(Integer.parseInt(page), Integer.parseInt(limit));

        IPage<HomeworkTeacher> classtaskIPage = homeworkTeacherService.page(classtaskPage, queryWrapper);
        List<HomeworkTeacher> classtasklists = homeworkTeacherService.list(queryWrapper);
        WebResponse webResponse = new WebResponse();
        webResponse.setData(classtaskIPage.getRecords());
        webResponse.setMsg("success");
        webResponse.setCode(0);
        webResponse.setCount(classtasklists.size());
        return webResponse;
    }

    /**
     * @return 三级列表，课程下，作业下，作业提交情况
     * @description
     * @author 若凡
     * @date 2020/7/20 14:50
     */
    @RequestMapping("/classtaskdetail")
    public WebResponse gettaskdetail(HttpServletRequest request, String limit, String page) {
        HttpSession session = request.getSession();
        String teacherHomewordId = session.getAttribute("teacherHomewordId").toString();

        QueryWrapper<HomeworkStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("homework_teacher_id", teacherHomewordId);
        List<HomeworkStudent> tasklist = homeworkStudentService.list(queryWrapper);

        Page<HomeworkStudent> homeworkStudentPage = new Page<>(Integer.parseInt(page), Integer.parseInt(limit));

        IPage<HomeworkStudent> homeworkStudentIPage = homeworkStudentService.page(homeworkStudentPage, queryWrapper);

        WebResponse webResponse = new WebResponse();
        webResponse.setData(homeworkStudentIPage.getRecords());
        webResponse.setCount(tasklist.size());
        webResponse.setCode(0);
        webResponse.setMsg("success");
        return webResponse;
    }

    @RequestMapping("/teachertaskedit")
    public String teachertaskedit(HttpServletRequest request) {
        String taskid = request.getParameter("taskid");
        Taskid = taskid;
        return "/teacher/teachertaskedit";
    }

    //    @RequestMapping("/editteachertaskdetail")
//    @ResponseBody
//    public WebResponse editteachertaskdetail(@RequestBody String json){
//        String teacherid="1";
//        JSONObject jo = (JSONObject) JSONObject.parse(json);
//        System.out.println(jo);
//        Object qustitle=jo.getObject("qustitle", (TypeReference) String);
//        Object qusetion=jo.getObject("qusetion", (TypeReference) String);
//        //先找到所有信息
//        QueryWrapper<HomeworkTeacher> homeworkTeacherQueryWrapper=new QueryWrapper<>();
//        homeworkTeacherQueryWrapper.eq("id",Taskid);
//        List<HomeworkTeacher> homeworkTeacherlist=iHomeworkTeacherService.list();
//        HomeworkTeacher homeworkTeacher=homeworkTeacherlist.get(0);
//
//        homeworkTeacher.setId(Taskid);
//        homeworkTeacher.setContent((java.lang.String) qusetion);
//        homeworkTeacher.setTitle((java.lang.String) qustitle);
//
//        UpdateWrapper<HomeworkTeacher> homeworkTeacherUpdateWrapper = new UpdateWrapper<>();
//        homeworkTeacherUpdateWrapper.eq("id",Taskid);
//        System.out.println(Taskid);
//        System.out.println(homeworkTeacher);
//        int row = homeworkTeacherMapper.update(homeworkTeacher,homeworkTeacherUpdateWrapper);
//        WebResponse webResponse = new WebResponse();
//        if(row==1) {
//            webResponse.setMsg("修改成功");
//        }else {
//            webResponse.setMsg("修改失败");
//        }
//        webResponse.setCode(0);
//        return webResponse;
//    }
    @PostMapping("/deleteteachertask")
    public WebResponse deletecourse(String id) {
        QueryWrapper<HomeworkTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        boolean delete = homeworkTeacherService.removeById(id);
        WebResponse webResponse = new WebResponse();
        if (delete) {
            webResponse.setCode(0);
        } else {
            webResponse.setCode(1);
        }
        return webResponse;
    }

    /**
     * @return
     * @description 教师添加课程作业
     * @author 若凡
     * @date 2020/7/20 14:11
     */
    @PostMapping("/addtask")
    public WebResponse addtask(HttpServletRequest request, String qustitle, String qusetion) {
        HttpSession session = request.getSession();
        String courseId = session.getAttribute("courseId").toString();
        String teacherId = session.getAttribute("id").toString();
        //在course中找到teacher_id和teacherName
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", courseId);
        Course course = courseService.getOne(queryWrapper);


        //给homework——teacher中各个元素赋值
        HomeworkTeacher homeworkTeacher = new HomeworkTeacher();
        homeworkTeacher.setId(UUID.randomUUID().toString());
        homeworkTeacher.setCourseId(courseId);
        homeworkTeacher.setCourseName(course.getName());
        homeworkTeacher.setTeacherId(course.getTeacherId());
        homeworkTeacher.setTeacherName(course.getTeacherName());
        homeworkTeacher.setCreateTime(LocalDateTime.now());
        homeworkTeacher.setTitle(qustitle);
        homeworkTeacher.setContent(qusetion);

        if (session.getAttribute("teacherHomeworkFileSrc") != null) {
            homeworkTeacher.setFilesrc(session.getAttribute("teacherHomeworkFileSrc").toString());
            session.setAttribute("teacherHomeworkFileSrc", null);
        }

        boolean insert = homeworkTeacherService.save(homeworkTeacher);
        String msg = null;
        if (insert) {
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }
        WebResponse webResponse = new WebResponse();
        webResponse.setCode(0);
        webResponse.setMsg(msg);
        return webResponse;
    }

    /**
     * @return
     * @description 老师作业上传附件
     * @author 若凡
     * @date 2020/7/21 8:58
     */
    @PostMapping("/upload")
    public WebResponse upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
//        String id = request.getSession().getAttribute("id").toString();
        byte[] bytes = file.getBytes();
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = file.getName();
        long fileSize = file.getSize();
        System.out.println(originalFileName + "==========" + fileName + "===========" + fileSize + "===============" + extension + "====" + bytes.length);
        String filesrc = fastdfsUtil.uploadFile(bytes, fileSize, extension);
        request.getSession().setAttribute("teacherHomeworkFileSrc", filesrc);
        request.getSession().setAttribute("teacherHomeworkFileName", originalFileName);
        WebResponse webResponse = new WebResponse();
        webResponse.setData(originalFileName);
        return webResponse;
    }

    /**
     * @description
     * 老师作业下载附件
     * @return
     * @author 若凡
     * @date 2020/7/21 9:08
     */
    @GetMapping("/download")
    public void downloadFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",id);
//        Notice one = noticeService.getOne(queryWrapper);
        String teacherHomeworkFileSrc = request.getSession().getAttribute("teacherHomeworkFileSrc").toString();
        Object teacherHomeworkFileName = request.getSession().getAttribute("teacherHomeworkFileName");
        byte[] bytes = fastdfsUtil.downloadFile(teacherHomeworkFileSrc);
        // 这里只是为了整合fastdfs，所以写死了文件格式。需要在上传的时候保存文件名。下载的时候使用对应的格式
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(teacherHomeworkFileName+"", "UTF-8"));
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
    
    /**
     * @description 
     * 學生查看作业详情
     * @return
     * @author 若凡
     * @date 2020/7/21 10:45
     */
    @GetMapping("/getHomeworkTeacherById")
    public HomeworkTeacher getHomeworkTeacherById(HttpServletRequest request){
        HttpSession session = request.getSession();
        String studentTeacherHomeworkId = session.getAttribute("studentTeacherHomeworkId").toString();
        QueryWrapper<HomeworkTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",studentTeacherHomeworkId);
        HomeworkTeacher one = homeworkTeacherService.getOne(queryWrapper);
        return one;
    }


    /**
     * @description
     * 学生下载老师作业附件
     * @return
     * @author 若凡
     * @date 2020/7/21 9:08
     */
    @GetMapping("/studentDownloadHomeworkTeacher")
    public void studentDownloadHomeworkTeacher(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",id);
//        Notice one = noticeService.getOne(queryWrapper);
        HttpSession session = request.getSession();
        String studentTeacherHomeworkId = session.getAttribute("studentTeacherHomeworkId").toString();
        QueryWrapper<HomeworkTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",studentTeacherHomeworkId);
        HomeworkTeacher one = homeworkTeacherService.getOne(queryWrapper);
        byte[] bytes = fastdfsUtil.downloadFile(one.getFilesrc());
        // 这里只是为了整合fastdfs，所以写死了文件格式。需要在上传的时候保存文件名。下载的时候使用对应的格式
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(one.getTitle()+".rar", "UTF-8"));
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

