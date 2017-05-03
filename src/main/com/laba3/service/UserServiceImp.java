package com.laba3.service;

import com.laba3.dao.UserDao;
import com.laba3.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by set on 23.04.17.
 */

@Service
public class UserServiceImp implements UserService {

    final static Logger logger = Logger.getLogger(UserServiceImp.class);

    @Autowired
    @Qualifier("userDao")
    private  UserDao userDAO;

//    @Autowired
//    public UserServiceImp(UserDao userDao) {this.userDAO = userDao;}

//    public  UserDao getUserDAO() {
//        return userDAO;
//    }

    public void setUserDAO(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User authentication (String login, String password) {
        User user = userDAO.findUserByLoginAndPassword(login, password);
        logger.debug("user: " + user);

        if (user == null)  return null;
        return user;
    }

    @Override
    public List<User> getAllUser() throws ClassNotFoundException {
        return  userDAO.getAll();
    }

    @Override
    public long addUser(User user) {
        return userDAO.insert(user);
    }

    @Override
    public void deleteByIdUser(int id) {
        userDAO.deleteById(id);
    }
}
