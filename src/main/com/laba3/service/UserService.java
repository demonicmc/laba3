package com.laba3.service;

import com.laba3.pojo.User;

import java.util.List;

/**
 * Created by set on 23.04.17.
 */
public interface UserService {

    User authentication (String login, String password);
    List<User> getAllUser() throws ClassNotFoundException;
    long addUser (User user);

}
