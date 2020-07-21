package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.entity.Course;
import com.example.demo.dao.entity.Notice;
import com.example.demo.dao.entity.StudentCourse;
import com.example.demo.dao.entity.User;
import com.example.demo.service.ICourseService;
import com.example.demo.service.INoticeService;
import com.example.demo.service.IStudentCourseService;
import com.example.demo.dao.web.WebResponse;
import com.example.demo.utils.FastdfsUtil;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private INoticeService noticeService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IStudentCourseService studentCourseService;
    @Autowired
    private FastdfsUtil fastdfsUtil;

    @ApiOperation("学生通知列表")
    @GetMapping("/student")
    public WebResponse student(String page, String limit, HttpSession session) {
        User user = (User) session.getAttribute("user");
        QueryWrapper<StudentCourse> studentCoursequeryWrapper = new QueryWrapper<>();
        studentCoursequeryWrapper.eq("student_id", user.getId());
        List<StudentCourse> studentCourseList = studentCourseService.list(studentCoursequeryWrapper);
        if (studentCourseList.size() > 0) {
            QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
            for (StudentCourse studentCourse : studentCourseList) {
                System.out.println("id" + studentCourse.getCourseId());
                noticeQueryWrapper.or().eq("course_id", studentCourse.getCourseId());
            }
            Page<Notice> noticePage = new Page<>(Integer.parseInt(page), Integer.parseInt(limit));
            IPage<Notice> noticeIPage = noticeService.page(noticePage, noticeQueryWrapper);
            List<Notice> noticeList = noticeService.list(noticeQueryWrapper);
            WebResponse<Object> webResponse = new WebResponse<>();
            webResponse.setData(noticeIPage.getRecords());
            webResponse.setCount(noticeList.size());
            webResponse.setCode(0);
            return webResponse;
        } else {
            WebResponse<Object> webResponse = new WebResponse<>();
            webResponse.setData(null);
            webResponse.setCount(0);
            webResponse.setCode(0);
            return webResponse;
        }
    }

    @ApiOperation("查看通知")
    @GetMapping("/getStudentNoticeById")
    public Notice getStudentNoticeById(HttpSession session) {
        String noticeId = (String) session.getAttribute("noticeId");
        return noticeService.getById(noticeId);
    }

    @ApiOperation("教师通知列表")
    @GetMapping("/teacher")
    public WebResponse teacher(String page, String limit, HttpSession session) {
        User user = (User) session.getAttribute("user");
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", user.getId());
        List<Notice> notices = noticeService.list(queryWrapper);
        if (notices.size() > 0) {
            Page<Notice> noticePage = new Page<>(Integer.parseInt(page), Integer.parseInt(limit));
            IPage<Notice> noticeIPage = noticeService.page(noticePage, queryWrapper);

            WebResponse<Object> webResponse = new WebResponse<>();
            webResponse.setData(noticeIPage.getRecords());
            webResponse.setCount(notices.size());
            webResponse.setCode(0);
            return webResponse;
        } else {
            WebResponse<Object> webResponse = new WebResponse<>();
            webResponse.setData(null);
            webResponse.setCount(0);
            webResponse.setCode(0);
            return webResponse;
        }
    }

    @ApiOperation("获取课程名列表")
    @GetMapping("/getCourseName")
    public List<Course> getCourseName(HttpSession session) {
        User user = (User) session.getAttribute("user");
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id", user.getId());
        List<Course> courseList = courseService.list(courseQueryWrapper);
        return courseList;
    }

    @ApiOperation("发布通知")
    @PostMapping("/create")
    public WebResponse createNotice(Notice notice, HttpSession session) {
        User user = (User) session.getAttribute("user");
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("id", notice.getCourseId());
        courseQueryWrapper.eq("teacher_id", user.getId());
        Course course = courseService.getOne(courseQueryWrapper);

        notice.setId(UUID.randomUUID().toString());
        notice.setTeacherId(user.getId());
        notice.setTeacherName(user.getName());
        notice.setCourseName(course.getName());
        notice.setCreateTime(LocalDateTime.now());

        if (session.getAttribute("noticeFileSrc") != null) {
            notice.setFilesrc(session.getAttribute("noticeFileSrc").toString());
            session.setAttribute("noticeFileSrc", null);
        }

        Boolean b = noticeService.save(notice);
        if (b) {
            return new WebResponse(0, "发布成功！");
        } else {
            return new WebResponse(1, "发布失败，请重试");
        }
    }

    @ApiOperation("查看通知")
    @GetMapping("/getTeacherNoticeById")
    public Notice getTeacherNoticeById(HttpSession session) {
        String noticeId = (String) session.getAttribute("noticeId");
        return noticeService.getById(noticeId);
    }

    @ApiOperation("修改通知")
    @PostMapping("/edit")
    public WebResponse edit(Notice notice, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String noticeId = (String) session.getAttribute("noticeId");
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("id", notice.getCourseId());
        courseQueryWrapper.eq("teacher_id", user.getId());
        Course course = courseService.getOne(courseQueryWrapper);
        notice.setId(noticeId);
        notice.setTeacherId(user.getId());
        notice.setTeacherName(user.getName());
        notice.setCourseName(course.getName());
        notice.setCreateTime(LocalDateTime.now());

        if (session.getAttribute("noticeFileSrc") != null) {
            notice.setFilesrc(session.getAttribute("noticeFileSrc").toString());
            session.setAttribute("noticeFileSrc", null);
        }
        Boolean b = noticeService.updateById(notice);
        if (b) {
            return new WebResponse(0, "修改成功！");
        } else {
            return new WebResponse(1, "修改失败，请重试");
        }
    }

    @ApiOperation("删除多个通知")
    @PostMapping("/delBatch")
    public String delBatchNotice(@RequestParam(value = "ids[]") String[] ids) {
        for (String id : ids) {
            noticeService.removeById(id);
        }
        return "删除成功！";
    }


    @ApiOperation("删除通知")
    @GetMapping("/del")
    public WebResponse delNotice(String noticeId) {
        noticeService.removeById(noticeId);
        WebResponse webResponse = new WebResponse();
        webResponse.setCode(0);
        webResponse.setMsg("删除成功！");
        return webResponse;
    }


    /**
     * @description 
     * 通知上传附件
     * @return 
     * @author 若凡
     * @date 2020/7/21 8:58
     */
    @PostMapping("/upload")
    public WebResponse upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        String id = request.getSession().getAttribute("id").toString();
        byte[] bytes = file.getBytes();
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = file.getName();
        long fileSize = file.getSize();
        System.out.println(originalFileName + "==========" + fileName + "===========" + fileSize + "===============" + extension + "====" + bytes.length);
        String filesrc = fastdfsUtil.uploadFile(bytes, fileSize, extension);
        request.getSession().setAttribute("noticeFileSrc", filesrc);
        WebResponse webResponse = new WebResponse();
        return webResponse;
    }

    /**
     * @description 
     * 通知下载附件
     * @return 
     * @author 若凡
     * @date 2020/7/21 9:08
     */
    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response, String id) throws IOException {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Notice one = noticeService.getOne(queryWrapper);
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
    
    /**
     * @description 
     * 学生下载通知附件
     * @return
     * @author 若凡
     * @date 2020/7/21 14:49
     */
    @GetMapping("/studentDownload")
    public void studentDownload(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String noticeId = request.getSession().getAttribute("noticeId").toString();
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",noticeId);
        Notice one = noticeService.getOne(queryWrapper);
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
    }

}

