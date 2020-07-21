package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.entity.Course;
import com.example.demo.dao.entity.StudentCourse;
import com.example.demo.dao.entity.User;
import com.example.demo.dao.mapper.CourseMapper;
import com.example.demo.service.IStudentCourseService;
import com.example.demo.service.IUserService;
import com.example.demo.dao.web.WebResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation("后台登录")
    @PostMapping("/login")
    private WebResponse login(User user, HttpSession session){
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        queryWrapper.eq("password",user.getPassword());
        User one=userService.getOne(queryWrapper);
        if(one==null){
            return new WebResponse(0,"账号或密码错误");
        }else {
            session.setAttribute("user",one);
            if(one.getRoleCode()==1){
                return new WebResponse(1,"登录成功",one);
            }else{
                return new WebResponse(2,"登录成功",one);
            }
        }
    }


    @Autowired
    private IUserService iUserService;
    @Autowired
    private IStudentCourseService iStudentCourseService;
    @Autowired
    private CourseMapper courseMapper;
    private Object String;

    //通过excel添加学生
    @ApiOperation(value="学生名单导入")
    @PostMapping("/importStudent")
    public WebResponse addstudent(@RequestParam("file") MultipartFile file, String courseId) throws IOException {
//        System.out.println(classid);
        Course course=courseMapper.selectById(courseId);
        String filename=file.getOriginalFilename();
        String message="success";
        InputStream is = file.getInputStream();
        Workbook wb = WorkbookFactory.create(is);
        Sheet sheet = wb.getSheetAt(0);
        if (sheet == null) {
            message = "Excel 表为空";
        }
        User student;
        StudentCourse studentCourse;
        QueryWrapper<User> queryWrapper;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null)
                continue;
//            student = new User();
            studentCourse = new StudentCourse();
            queryWrapper = new QueryWrapper<>();

            for(int j=0;j<1;j++){
                row.getCell(j).setCellType(CellType.STRING);
            }
//            row.getCell(1).setCellType((CellType) String);
//            row.getCell(2).setCellType((CellType) String);
//            row.getCell(3).setCellType((CellType) String);
//            row.getCell(4).setCellType((CellType) String);
//            row.getCell(5).setCellType((CellType) String);

            String username = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();
//            String sex = row.getCell(2).getStringCellValue();
//            String phone = row.getCell(4).getStringCellValue();
//            String email = row.getCell(5).getStringCellValue();
//            String studentid= UUID.randomUUID().toString();

            queryWrapper.eq("username",username);
            student = userService.getOne(queryWrapper);

//            student.setId(studentid);
//            student.setRoleCode(2);
//            student.setUsername(username);
//            student.setName(name);
//            student.setPassword("123456");
//            student.setSex(sex);
//            student.setPhone(phone);
//            student.setEmail(email);
//            student.setUsername(username);
//            System.out.println(student);
            studentCourse.setId(UUID.randomUUID().toString());
            studentCourse.setCourseId(courseId);
            studentCourse.setTeacherId(course.getTeacherId());
            studentCourse.setTeacherName(course.getTeacherName());
            studentCourse.setStudentId(student.getId());
            studentCourse.setStudentName(name);
            //field1存学号
            studentCourse.setField1(username);
            studentCourse.setCourseName(course.getName());
//            iUserService.save(student);
            iStudentCourseService.save(studentCourse);

        }
        WebResponse webResponse=new WebResponse();
        return webResponse;
    }
}

