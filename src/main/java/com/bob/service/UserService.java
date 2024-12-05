package com.bob.service;

import com.bob.pojo.User;

public interface UserService {
    public User findUserByUserName(String userName);

    public void register(String username, String password);
}
