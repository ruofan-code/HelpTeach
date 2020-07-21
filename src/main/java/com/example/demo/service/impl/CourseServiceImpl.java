package com.example.demo.service.impl;

import com.example.demo.dao.entity.Course;
import com.example.demo.dao.mapper.CourseMapper;
import com.example.demo.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 若凡
 * @since 2020-07-17
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

}
