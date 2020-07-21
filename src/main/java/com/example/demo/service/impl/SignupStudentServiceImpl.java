package com.example.demo.service.impl;

import com.example.demo.dao.entity.SignupStudent;
import com.example.demo.dao.mapper.SignupStudentMapper;
import com.example.demo.service.ISignupStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 若凡
 * @since 2020-07-19
 */
@Service
public class SignupStudentServiceImpl extends ServiceImpl<SignupStudentMapper, SignupStudent> implements ISignupStudentService {

}
