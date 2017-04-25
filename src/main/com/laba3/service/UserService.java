package main.com.laba3.service;

import main.com.laba3.pojo.User;

import java.util.List;

/**
 * Created by set on 23.04.17.
 */
public interface UserService {

    User authentication (String loggin, String password);
    List<User> getAllUser() throws ClassNotFoundException;

}
