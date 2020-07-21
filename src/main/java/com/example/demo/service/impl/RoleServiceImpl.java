package com.example.demo.service.impl;

import com.example.demo.dao.entity.Role;
import com.example.demo.dao.mapper.RoleMapper;
import com.example.demo.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
