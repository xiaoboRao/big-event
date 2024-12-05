package com.bob.service.impl;

import com.bob.mapper.UserMapper;
import com.bob.pojo.User;
import com.bob.service.UserService;
import com.bob.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByUserName(String userName) {
        User u = userMapper.findUserByUserName(userName);
        return u;
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username, md5String);

    }
}
