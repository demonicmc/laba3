package main.com.laba3.dao;

import main.com.laba3.pojo.User;

/**
 * Created by set on 23.04.17.
 */
public interface UserDao extends Dao<Long, User> {

    User findUserByLoginAndPassword(String loggin, String password);

}
