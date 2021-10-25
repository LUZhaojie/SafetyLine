package com.backend.db.service.impl;

import com.backend.db.bean.User;
import com.backend.db.mapper.UserMapper;
import com.backend.db.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {



}
