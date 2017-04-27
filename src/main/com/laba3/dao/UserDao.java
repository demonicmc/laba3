package com.laba3.dao;

import com.laba3.pojo.User;

import java.util.List;

/**
 * Created by set on 23.04.17.
 */
public interface UserDao extends Dao<Long, User> {

    User findUserByLoginAndPassword(String login, String password);
    List<User> getAll();

}
